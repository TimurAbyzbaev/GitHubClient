package com.example.githubclient.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubclient.domain.entities.UserEntity
import com.example.githubclient.domain.repos.UsersRepo

class UsersViewModel(
    private val usersRepo: UsersRepo
) : UsersContract.ViewModel {
    override val usersLiveData: LiveData<List<UserEntity>> = MutableLiveData()
    override val errorLiveData: LiveData<Throwable> = MutableLiveData()
    override val progressLiveData: LiveData<Boolean> = MutableLiveData()

    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        progressLiveData.mutable().postValue(true)
        usersRepo.getUsers(
            onSuccess = {
                progressLiveData.mutable().postValue(false)
                usersLiveData.mutable().postValue(it)
            },
            onError = {
                progressLiveData.mutable().postValue(false)
                errorLiveData.mutable().postValue(it)
            }
        )
    }

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T>{
        return this as? MutableLiveData<T> ?: throw IllegalStateException("It's not a MutableLiveData 0_o")
    }

}