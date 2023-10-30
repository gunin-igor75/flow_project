package com.github.gunin_igor75.flow_project.users

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.gunin_igor75.flow_project.databinding.ActivityUsers2Binding

class Users2Activity : AppCompatActivity() {

    private val binding by lazy {
        ActivityUsers2Binding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[UsersViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        observeViewModel()
        onClickListener()
    }


    private fun observeViewModel() {
        viewModel.users.observe(this) {
            binding.tvUsers.text = it.joinToString()
        }
    }

    private fun onClickListener() {
        binding.btAddUser.setOnClickListener {
            val user = binding.etInputUser.text.toString().trim()
            user.takeIf { it.isNotBlank() }
                ?.let {
                    viewModel.addUser(it)
                }
        }
    }


    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, Users2Activity::class.java)
        }
    }
}