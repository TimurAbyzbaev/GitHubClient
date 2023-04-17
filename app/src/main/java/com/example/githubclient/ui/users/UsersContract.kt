package com.example.githubclient.ui.users

import androidx.lifecycle.LiveData
import com.example.githubclient.domain.entities.UserEntity
import io.reactivex.rxjava3.core.Observable

interface UsersContract {

    /*interface View {
        fun showUsers(data: List<UserEntity>)
        fun showError(throwable: Throwable)
        fun showProgress(inProgress: Boolean)
    }*/

    interface ViewModel {
        val usersLiveData: Observable<List<UserEntity>>
        val errorLiveData: Observable<Throwable>
        val progressLiveData: Observable<Boolean>
        val openProfileLiveData: Observable<UserEntity>
        fun onRefresh()
        fun onUserClick(userEntity: UserEntity)
    }

}