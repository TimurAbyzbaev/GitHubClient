package com.example.githubclient.domain

import com.example.githubclient.domain.UserEntity

interface UsersRepo {
    //CRUD
    //Create
    //Read
    //Update
    //Delete

    //read
    fun getUsers(
        onSuccess:(List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

}