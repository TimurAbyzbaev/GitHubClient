package com.example.githubclient.data.retrofit

import com.example.githubclient.domain.entities.UserEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {
    @GET("users")
    fun getUsers(): Single<List<UserEntityDto>>
}

