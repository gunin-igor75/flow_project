package com.github.gunin_igor75.flow_project.crypto_app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.gunin_igor75.flow_project.R

class CryptoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crypto)
    }


    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, CryptoActivity::class.java)
        }
    }
}