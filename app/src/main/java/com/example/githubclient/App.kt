package com.example.githubclient

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.example.githubclient.data.retrofit.RetrofitUsersRepoImpl
import com.example.githubclient.domain.repos.UsersRepo

class App: Application() {
    val usersRepo: UsersRepo by lazy { RetrofitUsersRepoImpl() }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App