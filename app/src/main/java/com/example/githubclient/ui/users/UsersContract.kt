package com.example.githubclient.ui.users

import androidx.lifecycle.LiveData
import com.example.githubclient.domain.entities.UserEntity

interface UsersContract {

    /*interface View {
        fun showUsers(data: List<UserEntity>)
        fun showError(throwable: Throwable)
        fun showProgress(inProgress: Boolean)
    }*/

    interface ViewModel {
        val usersLiveData: LiveData<List<UserEntity>>
        val errorLiveData: LiveData<Throwable>
        val progressLiveData: LiveData<Boolean>
        val openProfileLiveData: LiveData<Unit>
        fun onRefresh()
        fun onUserClick(userEntity: UserEntity)
    }

}