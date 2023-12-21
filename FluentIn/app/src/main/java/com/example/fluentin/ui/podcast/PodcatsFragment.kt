package com.example.fluentin.ui.podcast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fluentin.R
import com.example.fluentin.data.remote.response.Channel

import com.example.fluentin.data.remote.response.NgobrolBareng

import com.example.fluentin.databinding.FragmentPodcatsBinding

import com.example.fluentin.ui.podcast.adapter.NameChannelAdapter
import com.example.fluentin.ui.podcast.adapter.NgobrolAdapter

class PodcatsFragment : Fragment() {

    private var _binding : FragmentPodcatsBinding? = null
    private val binding get() = _binding!!
    private val listNgobrol = ArrayList<NgobrolBareng>()
    private val listChannel = ArrayList<Channel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPodcatsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //menu kategori
        binding.rvNgobrolBareng.setHasFixedSize(true)
        listNgobrol.addAll(getListNgobrol())
        showRvNgobrol()
        //list conversations
        binding.rvNameChannel.setHasFixedSize(true)
        listChannel.addAll(getListChannel())
        showRvChannel()

    }


    private fun getListNgobrol(): ArrayList<NgobrolBareng> {
        val dataTitle = resources.getStringArray(R.array.data_name_negara)
        val dataFoto = resources.obtainTypedArray(R.array.data_bendera)
        val listNgobrol = ArrayList<NgobrolBareng>()
        for (i in dataTitle.indices) {
            val ngobrol =
                NgobrolBareng(
                    dataTitle[i],
                    dataFoto.getResourceId(i, -1)
                )
            listNgobrol.add(ngobrol)
        }
        return listNgobrol
    }

    private fun showRvNgobrol() {
        binding.rvNgobrolBareng.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val listNgobrolAdapter = NgobrolAdapter(listNgobrol)
        binding.rvNgobrolBareng.adapter = listNgobrolAdapter
       listNgobrolAdapter.setOnItemClickCallback(object : NgobrolAdapter.OnItemClickCallback {
           override fun onItemClicked(data: NgobrolBareng) {
               Toast.makeText(context, "Cooming soon..!!", Toast.LENGTH_SHORT).show()
           }
       })


    }




    private fun getListChannel(): ArrayList<Channel> {
        val dataRoomName = resources.getStringArray(R.array.data_name_channel)
        val dataFoto = resources.obtainTypedArray(R.array.data_foto_channel)
        val listChannel = ArrayList<Channel>()
        for (i in dataRoomName.indices) {
            val channel =
                Channel(
                    dataRoomName[i],
                    dataFoto.getResourceId(i, -1)
                )
            listChannel.add(channel)
        }
        return listChannel
    }

    private fun showRvChannel() {
        binding.rvNameChannel.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val listChannelAdapter = NameChannelAdapter( listChannel)
        binding.rvNameChannel.adapter = listChannelAdapter
        listChannelAdapter.setOnItemClickCallback(object : NameChannelAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Channel) {
                Toast.makeText(context, "Cooming soon..!!", Toast.LENGTH_SHORT).show()
            }
        })


    }


}