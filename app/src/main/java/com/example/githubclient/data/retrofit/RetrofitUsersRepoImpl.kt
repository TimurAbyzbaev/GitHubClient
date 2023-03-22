package com.example.githubclient.data.retrofit

import android.annotation.SuppressLint
import com.example.githubclient.domain.entities.UserEntity
import com.example.githubclient.domain.repos.UsersRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitUsersRepoImpl: UsersRepo {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()


    private val api: GithubApi = retrofit.create(GithubApi::class.java)

    @SuppressLint("CheckResult")
    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        api.getUsers().subscribeBy (
            onSuccess = {
                onSuccess.invoke(it)
            },
            onError = {
                onError?.invoke(it)
            }
        )
    }

    override fun getUsers(): Single<List<UserEntity>> = api.getUsers()
}