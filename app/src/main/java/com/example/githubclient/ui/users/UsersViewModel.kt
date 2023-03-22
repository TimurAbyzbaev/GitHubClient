package com.example.githubclient.ui.users

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubclient.domain.entities.UserEntity
import com.example.githubclient.domain.repos.UsersRepo
import com.example.githubclient.utils.SingleEventLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject

class UsersViewModel(
    private val usersRepo: UsersRepo
) : UsersContract.ViewModel {
    override val usersLiveData: Observable<List<UserEntity>> = BehaviorSubject.create()
    override val errorLiveData: Observable<Throwable> = BehaviorSubject.create() //single event
    override val progressLiveData: Observable<Boolean> = BehaviorSubject.create()
    override val openProfileLiveData: Observable<Unit> =  BehaviorSubject.create()//single event

    override fun onRefresh() {
        loadData()
    }

    override fun onUserClick(userEntity: UserEntity) {
        openProfileLiveData
    }

    @SuppressLint("CheckResult")
    private fun loadData() {
        progressLiveData.mutable().onNext(true)

        usersRepo.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
            onSuccess = {
                progressLiveData.mutable().onNext(false)
                usersLiveData.mutable().onNext(it)
            },
            onError = {
                progressLiveData.mutable().onNext(false)
                errorLiveData.mutable().onNext(it)
            }
        )

    }

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T>{
        return this as? MutableLiveData<T> ?: throw IllegalStateException("It's not a MutableLiveData 0_o")
    }

    private fun <T : Any> Observable<T>.mutable(): Subject<T> {
        return this as? Subject<T> ?: throw IllegalStateException("It's not a MutableLiveData 0_o")
    }

}