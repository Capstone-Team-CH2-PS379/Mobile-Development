package com.example.fluentin.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.example.fluentin.data.Result
import com.example.fluentin.data.PreferenceDataSource
import com.example.fluentin.data.UserSharedPreferences
import com.example.fluentin.data.remote.response.SkorResponse
import com.example.fluentin.databinding.FragmentProfileBinding
import com.example.fluentin.ui.editprofile.UpdateProfileActivity
import com.example.fluentin.ui.login.LoginActivity
import com.example.fluentin.utils.ViewModelFactory


class ProfileFragment : Fragment() {

    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val userSharedPreferences = UserSharedPreferences

    private val viewModel: ProfileViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    private val pref : PreferenceDataSource by lazy {
        PreferenceDataSource.invoke(requireContext())
    }





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnLogout.setOnClickListener {
            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Are you sure you want to logout?")
                .setPositiveButton("Yes"){_,_ ->
                    pref.deleteDataAuth()
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                .setNegativeButton("No", null)
            val alert = alertDialog.create()
            alert.show()
        }


        val userId = userSharedPreferences.getUserId(requireContext())

        Log.d("USER ID DI HALAMAN PROFILE : ", userId)


        binding.btnEditProfile.setOnClickListener {
            val intent = Intent(requireContext(), UpdateProfileActivity::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)
        }


        viewModel.getUserSkor(userId).observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    showLoading(false)
                    val skorResponse: SkorResponse = result.data
                    val namaUser: String = skorResponse.data.fullName
                    val pointUser: String = skorResponse.data.skor

                    Log.d("NAMA USER DI PROFILE", "$namaUser")
                    Log.d("POINT USER DI PROFILE", "$pointUser")

                    binding.tvPointUser.text = pointUser
                    binding.profileName.text = namaUser

                    if (pointUser.isNullOrEmpty()) {
                        binding.tvPointUser.text = "0"
                    } else {
                        binding.tvPointUser.text = pointUser
                    }
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



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}