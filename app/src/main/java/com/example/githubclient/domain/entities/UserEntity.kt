package com.example.githubclient.domain.entities

import com.google.gson.annotations.SerializedName

data class UserEntity(
    val login: String,
    val id: Long,
    @SerializedName("avatar_url")
    val avatarUrl: String
)