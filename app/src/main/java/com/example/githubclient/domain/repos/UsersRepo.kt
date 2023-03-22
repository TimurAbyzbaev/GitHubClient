package com.example.githubclient.domain.repos

import com.example.githubclient.domain.entities.UserEntity
import io.reactivex.rxjava3.core.Single

interface UsersRepo {
    //CRUD
    //Create
    //Read
    //Update
    //Delete

    //read
    //Callback
    fun getUsers(
        onSuccess:(List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

    //RX
    fun getUsers(): Single<List<UserEntity>>

}