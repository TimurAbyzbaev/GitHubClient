package com.example.githubclient.data.retrofit

import com.example.githubclient.domain.entities.UserEntity
import com.example.githubclient.domain.repos.UsersRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitUsersRepoImpl: UsersRepo {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private val service: GithubApi = retrofit.create(GithubApi::class.java)

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        service.getUsers()?.enqueue(object : Callback<List<UserEntity>>{
            override fun onResponse(
                call: Call<List<UserEntity>>,
                response: Response<List<UserEntity>>
            ) {
                val body = response.body()
                if(response.isSuccessful && body != null){
                    onSuccess(body)
                }
                else{
                    onError?.invoke(java.lang.IllegalStateException("Данных нет или ошибка"))
                }
            }

            override fun onFailure(call: Call<List<UserEntity>>, t: Throwable) {
                onError?.invoke(t)
            }

        })
    }
}