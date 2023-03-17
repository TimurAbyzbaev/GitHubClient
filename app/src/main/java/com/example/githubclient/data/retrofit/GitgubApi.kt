package com.example.githubclient.data.retrofit

import com.example.githubclient.domain.entities.UserEntity
import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {
  @GET("users")
  fun getUsers(): Call<List<UserEntity>>?
}

