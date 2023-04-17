package com.example.githubclient.ui.users

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubclient.R
import com.example.githubclient.app
import com.example.githubclient.domain.entities.UserEntity
import com.example.githubclient.databinding.ActivityMainBinding
import com.example.githubclient.ui.profile.ProfileActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter {
        viewModel.onUserClick(it)
    }
    private lateinit var viewModel: UsersContract.ViewModel
    private val viewModelDisposable: CompositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initViewModel()

    }

    @SuppressLint("CheckResult")
    private fun initViewModel() {
        viewModel = extractViewModel()

        viewModelDisposable.addAll(
            viewModel.progressLiveData.subscribe{ showProgress(it) },
                    viewModel.usersLiveData.subscribe{ showUsers(it) },
                    viewModel.errorLiveData.subscribe{ showError(it) },
                    viewModel.openProfileLiveData.subscribe { openProfileScreen(it) }
        )
    }

    override fun onDestroy() {
        viewModelDisposable.dispose()
        super.onDestroy()
    }
    private fun openProfileScreen(userEntity: UserEntity){
        val myIntent = Intent(this, ProfileActivity::class.java)
        myIntent.putExtra("USER", userEntity)
        startActivity(myIntent)
    }

    private fun extractViewModel(): UsersContract.ViewModel {
        return lastCustomNonConfigurationInstance as? UsersContract.ViewModel
            ?: UsersViewModel(app.usersRepo)
    }

    override fun onRetainCustomNonConfigurationInstance(): UsersContract.ViewModel? {
        return viewModel
    }

    private fun initViews() {
        showProgress(false)
        binding.refreshButton.setOnClickListener {
            viewModel.onRefresh()
        }
        initRecycleView()
    }

    private fun showUsers(data: List<UserEntity>) {
        adapter.setData(data)
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun initRecycleView() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.usersRecyclerView.adapter = adapter
    }

    private fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.usersRecyclerView.isVisible = !inProgress
    }
}