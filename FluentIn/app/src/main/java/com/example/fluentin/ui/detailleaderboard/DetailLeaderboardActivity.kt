package com.example.fluentin.ui.detailleaderboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.fluentin.R
import com.example.fluentin.databinding.ActivityDetailLeaderboardBinding
import com.example.fluentin.utils.ViewModelFactory
import com.example.fluentin.data.Result
import com.example.fluentin.data.remote.response.LeaderboardData
import com.example.fluentin.data.remote.response.LeaderboardResponse
import com.example.fluentin.data.remote.response.LeaderboardResponseDetail


class DetailLeaderboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailLeaderboardBinding
    private val viewModel by viewModels<DetailLeaderboardViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailLeaderboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = intent.getStringExtra(EXTRA_ID)
        val name = intent.getStringExtra(EXTRA_NAME)
        val point = intent.getStringExtra(EXTRA_POINTS)

        binding.detailName.text = name
        binding.tvPointUser.text = point

        Log.d("USER ID DETAIL LEADERBOARD", "$userId")

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun message(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean){
        binding.progressBar2.visibility = if (isLoading) View.VISIBLE else View.GONE
    }




    companion object{
        const val EXTRA_ID = "EXTRA_ID"
        const val EXTRA_NAME = "EXTRA_NAME"
        const val EXTRA_POINTS = "EXTRA_POINTS"
    }
}