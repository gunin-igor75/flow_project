package com.github.gunin_igor75.flow_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.gunin_igor75.flow_project.databinding.ActivityMainBinding
import com.github.gunin_igor75.flow_project.crypto_app.CryptoActivity
import com.github.gunin_igor75.flow_project.users.UsersActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        launchOnClickListener()


    }

    private fun launchOnClickListener() {
        binding.btUsersActivity.setOnClickListener {
            val intent = UsersActivity.newIntent(this)
            startActivity(intent)
        }
        binding.btCryptoActivity.setOnClickListener {
            val intent = CryptoActivity.newIntent(this)
            startActivity(intent)
        }
    }
}