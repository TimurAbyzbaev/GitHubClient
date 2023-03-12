package com.example.githubclient.ui.users

import com.example.githubclient.domain.entities.UserEntity

interface UsersContract {

    interface View {
        fun showUsers(data: List<UserEntity>)
        fun showError(throwable: Throwable)
        fun showProgress(inProgress: Boolean)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()

        fun onRefresh()
    }

}