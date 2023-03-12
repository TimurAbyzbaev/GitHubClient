package com.example.githubclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isVisible
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
        showProgress(false)
        binding.refreshButton.setOnClickListener {
            showProgress(true)
            usersRepo.getUsers(
                onSuccess = {
                    showProgress(false)
                    adapter.setData(it)
                },
                onError = {
                    showProgress(false)
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            )
        }
        initRecycleView()
    }
    private fun initRecycleView() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.usersRecyclerView.adapter = adapter
    }

    private fun showProgress(inProgress: Boolean){
        binding.progressBar.isVisible = inProgress
        binding.usersRecyclerView.isVisible = !inProgress
    }
}