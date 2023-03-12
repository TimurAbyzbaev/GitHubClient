package com.example.githubclient.domain.repos

import com.example.githubclient.domain.entities.UserEntity

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