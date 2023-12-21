package com.example.fluentin.ui.leaderboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fluentin.R
import com.example.fluentin.data.PreferenceDataSource
import com.example.fluentin.databinding.FragmentHomeBinding
import com.example.fluentin.databinding.FragmentLeaderboardBinding
import com.example.fluentin.databinding.FragmentProfileBinding
import com.example.fluentin.ui.profile.ProfileViewModel
import com.example.fluentin.utils.ViewModelFactory
import com.example.fluentin.data.Result


class LeaderboardFragment : Fragment() {

    private var _binding : FragmentLeaderboardBinding? = null
    private val binding get() = _binding!!


    private val viewModel: LeaderboardViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    private val pref : PreferenceDataSource by lazy {
        PreferenceDataSource.invoke(requireContext())
    }

    private lateinit var adapter: LeaderboardAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLeaderboardBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = LeaderboardAdapter()
        binding.apply {
            rvLeaderboard.layoutManager = LinearLayoutManager(requireContext())
            rvLeaderboard.setHasFixedSize(true)
            rvLeaderboard.adapter = adapter
        }

        adapter.notifyDataSetChanged()

        viewModel.getLeaderboardList().observe(requireActivity()){result ->
            when(result){
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    showLoading(false)
                    val allLeaderboard = result.data
                    adapter.setAllLeaderboardList(allLeaderboard)
                }
                is Result.Error -> {
                    showLoading(false)
                    message(result.error)
                }
            }
        }


    }


    private fun message(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


    private fun showLoading(isLoading: Boolean){
        binding.progressBar2.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}