package com.github.gunin_igor75.flow_project.game_score

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.github.gunin_igor75.flow_project.databinding.ActivityGameScoreBinding
import kotlinx.coroutines.launch

class GameScoreActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityGameScoreBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[GameScoreViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        observeViewModel()
        launchOnClickListener()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is State.Game -> {
                            binding.tvTeam1Score.text = state.score1.toString()
                            binding.tvTeam2Score.text = state.score2.toString()
                        }

                        is State.Winner -> {
                            binding.tvTeam1Score.text = state.score1.toString()
                            binding.tvTeam2Score.text = state.score2.toString()
                            Toast.makeText(
                                this@GameScoreActivity,
                                "Winner ${state.winnerTeam}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun launchOnClickListener() {
        binding.tvTeam1.setOnClickListener {
            viewModel.increaseScore(Team.TEAM_1)
        }
        binding.tvTeam2.setOnClickListener {
            viewModel.increaseScore(Team.TEAM_2)
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, GameScoreActivity::class.java)
        }
    }
}