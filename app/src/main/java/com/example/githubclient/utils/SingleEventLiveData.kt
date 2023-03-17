package com.example.githubclient.utils

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleEventLiveData<T>: MutableLiveData<T>() {
    private val pending = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if(hasObservers()){
            Log.w(TAG, "Multiple observers registered but only one will be notified")
        }
        super.observe(owner, Observer { t ->
            if(pending.compareAndSet(true, false)){
                observer.onChanged(t)
            }
        })
    }

    override fun setValue(value: T) {
        pending.set(true)
        super.setValue(value)
    }

    companion object{
        private val TAG = "SingleLiveEvent"
    }
}