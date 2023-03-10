package com.example.githubclient

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