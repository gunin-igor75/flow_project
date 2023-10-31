package com.github.gunin_igor75.flow_project.crypto_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.github.gunin_igor75.flow_project.databinding.ActivityCryptoBinding
import kotlinx.coroutines.launch

class CryptoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCryptoBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[CryptoViewModel::class.java]
    }

    private val adapter = CryptoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()
        launchRefreshList()
    }

    private fun launchRefreshList() {
        binding.btRefresh.setOnClickListener {
            viewModel.refreshList()
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collect {
                    when (it) {
                        is State.Initial -> {
                            binding.pbLoadingCrypto.isVisible = false
                            binding.btRefresh.isEnabled = false
                        }

                        is State.Loading -> {
                            binding.pbLoadingCrypto.isVisible = true
                            binding.btRefresh.isEnabled = false
                        }

                        is State.Content -> {
                            binding.pbLoadingCrypto.isVisible = false
                            binding.btRefresh.isEnabled = true
                            adapter.submitList(it.currencyList)
                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvListCrypto.adapter = adapter
        binding.rvListCrypto.animation = null
    }


    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, CryptoActivity::class.java)
        }
    }

    //    private fun observeViewModel() {
//        lifecycleScope.launch {
//            viewModel.state
//                .flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED)
//                .collect {
//                    when (it) {
//                        is State.Initial -> {
//                            binding.pbLoadingCrypto.isVisible = false
//                        }
//
//                        is State.Loading -> {
//                            binding.pbLoadingCrypto.isVisible = true
//                        }
//
//                        is State.Content -> {
//                            binding.pbLoadingCrypto.isVisible = false
//                            adapter.submitList(it.currencyList)
//                        }
//                    }
//                }
//        }
//    }
}