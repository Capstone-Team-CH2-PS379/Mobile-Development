package com.example.fluentin.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fluentin.R
import com.example.fluentin.databinding.ActivityMainBinding
import com.example.fluentin.ui.home.HomeFragment
import com.example.fluentin.ui.leaderboard.LeaderboardFragment
import com.example.fluentin.ui.podcast.PodcatsFragment
import com.example.fluentin.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNav = binding.navBottom

        loadFragment(HomeFragment())

        // Bottom Navigation Listener
        bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    loadFragment(HomeFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.menu_ngobrol_bareng -> {
                    loadFragment(PodcatsFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.menu_leaderboard -> {
                    loadFragment(LeaderboardFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.menu_profile -> {
                    loadFragment(ProfileFragment())
                    return@setOnItemSelectedListener true
                }
                else -> false
            }
        }

    }

    private fun loadFragment(fragment: Fragment) {
        val transcation = supportFragmentManager.beginTransaction()
        transcation.replace(R.id.mainContainer,fragment)
        transcation.addToBackStack(null)
        transcation.commit()
    }
}