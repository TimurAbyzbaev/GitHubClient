package com.example.githubclient

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.githubclient.databinding.ItemUserBinding

class UsersViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
) {
    private val binding = ItemUserBinding.bind(itemView)

    fun bind(userEntity: UserEntity){
        binding.avatarImageView.load(userEntity.avatarUrl)
        binding.loginTextView.text = userEntity.login
        binding.uidTextView.text = userEntity.id.toString()
    }
}