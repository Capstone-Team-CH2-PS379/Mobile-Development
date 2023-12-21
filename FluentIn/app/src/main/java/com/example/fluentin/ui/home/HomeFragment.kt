package com.example.fluentin.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fluentin.R
import com.example.fluentin.data.remote.response.Conversations
import com.example.fluentin.data.remote.response.Kategori
import com.example.fluentin.databinding.FragmentHomeBinding
import com.example.fluentin.databinding.FragmentProfileBinding
import com.example.fluentin.ui.home.adapter.ConversationsAdapter
import com.example.fluentin.ui.home.adapter.KategoriAdapter
import com.example.fluentin.ui.pronunciation.PronunciationActivity

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val list = ArrayList<Kategori>()
    private val listConversations = ArrayList<Conversations>()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //menu kategori
        binding.rvMenu.setHasFixedSize(true)
        list.addAll(getListKategori())
        showRvLisMenu()

        //list conversations
        binding.rvConversations.setHasFixedSize(true)
        listConversations.addAll(getListConversations())
        showRvConversations()

        binding.btnStart.setOnClickListener{
            val intent = Intent(requireContext(), PronunciationActivity::class.java )
            startActivity(intent)
        }
    }

    private fun getListKategori(): ArrayList<Kategori> {
        val dataKategoriName = resources.getStringArray(R.array.data_kategori)
        val listKategori = ArrayList<Kategori>()
        for (i in dataKategoriName.indices) {
            val kategori =
                Kategori(
                    dataKategoriName[i]
                )
            listKategori.add(kategori)
        }
        return listKategori
    }

    private fun showRvLisMenu() {
        binding.rvMenu.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val listMenuAdapter = KategoriAdapter(list)
        binding.rvMenu.adapter = listMenuAdapter
        listMenuAdapter.setOnItemClickCallback(object : KategoriAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Kategori) {
                Toast.makeText(context, "Cooming soon..!!", Toast.LENGTH_SHORT).show()
            }
        })


    }

    private fun getListConversations(): ArrayList<Conversations> {
        val dataRoomName = resources.getStringArray(R.array.data_conversations_room)
        val dataDeskripsiRoom = resources.getStringArray(R.array.data_conversations_deskripsi)
        val listConversations = ArrayList<Conversations>()
        for (i in dataRoomName.indices) {
            val conversations =
                Conversations(
                    dataRoomName[i],
                    dataDeskripsiRoom[i],
                )
            listConversations.add(conversations)
        }
        return listConversations
    }

    private fun showRvConversations() {
        binding.rvConversations.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val listConversationsAdapter = ConversationsAdapter(listConversations)
        binding.rvConversations.adapter = listConversationsAdapter
        listConversationsAdapter.setOnItemClickCallback(object : ConversationsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Conversations) {
                Toast.makeText(context, "Cooming soon..!!", Toast.LENGTH_SHORT).show()
            }
        })


    }



}