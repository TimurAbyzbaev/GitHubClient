package com.example.githubclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubclient.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter()
    private val usersRepo: UsersRepo = FakeUsersRepoImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.refreshButton.setOnClickListener{
            Toast.makeText(this, "Hello!", Toast.LENGTH_SHORT).show()
        }
        initRecycleView()
    }

    private fun initRecycleView(){
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.usersRecyclerView.adapter = adapter
        usersRepo.getUsers(
            onSuccess = adapter::setData,
            onError = {
                Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
            }
        )

    }
}