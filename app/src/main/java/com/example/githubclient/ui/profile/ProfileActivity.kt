package com.example.githubclient.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import coil.load
import com.example.githubclient.R
import com.example.githubclient.databinding.ActivityProfileBinding
import com.example.githubclient.domain.entities.UserEntity

class ProfileActivity() : AppCompatActivity() {
    private lateinit var userEntity: UserEntity
    companion object{
        //val userEntity: UserEntity = UserEntity("login",1, "URL")
    }
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userEntity = intent.getSerializableExtra("USER") as UserEntity
        initView()
    }

    private fun initView() {
        binding.login.text = userEntity.login
        binding.avatar.load(userEntity.avatarUrl)
    }


}