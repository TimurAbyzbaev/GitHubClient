package com.example.githubclient.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubclient.domain.entities.UserEntity
import com.example.githubclient.domain.repos.UsersRepo
import com.example.githubclient.utils.SingleEventLiveData

class UsersViewModel(
    private val usersRepo: UsersRepo
) : UsersContract.ViewModel {
    override val usersLiveData: LiveData<List<UserEntity>> = MutableLiveData()
    override val errorLiveData: LiveData<Throwable> = SingleEventLiveData() //single event
    override val progressLiveData: LiveData<Boolean> = MutableLiveData()
    override val openProfileLiveData: LiveData<Unit> =  SingleEventLiveData() //single event

    override fun onRefresh() {
        loadData()
    }

    override fun onUserClick(userEntity: UserEntity) {
        openProfileLiveData.mutable().postValue(Unit)
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