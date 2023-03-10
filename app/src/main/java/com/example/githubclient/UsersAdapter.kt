package com.example.githubclient

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class UsersAdapter: RecyclerView.Adapter<UsersViewHolder>() {
    private val data = mutableListOf<UserEntity>()

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = getItem(position).id

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder = UsersViewHolder(parent)

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = data.size

    private fun getItem(pos: Int): UserEntity = data[pos]


    @SuppressLint("NotifyDataSetChanged")
    fun setData(users: List<UserEntity>){
        data.clear()
        data.addAll(users)
        notifyDataSetChanged()
    }
}