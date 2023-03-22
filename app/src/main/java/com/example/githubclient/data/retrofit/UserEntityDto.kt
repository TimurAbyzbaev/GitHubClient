package com.example.githubclient.data.retrofit

import com.example.githubclient.domain.entities.UserEntity
import com.google.gson.annotations.SerializedName

data class UserEntityDto(
    val login: String,
    val id: Long,
    @SerializedName("avatar_url")
    val avatarUrl: String
) {
    fun toUserEntity() = UserEntity(login, id, avatarUrl)

    companion object {
        fun fromUserEntity(userEntity: UserEntity): UserEntityDto {
            return UserEntityDto(
                userEntity.login,
                userEntity.id,
                userEntity.avatarUrl
            )
        }
    }
}

