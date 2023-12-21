package com.example.fluentin.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.fluentin.R
import com.example.fluentin.data.remote.response.Conversations
import com.example.fluentin.data.remote.response.Kategori
import com.example.fluentin.databinding.ItemMenuHomeBinding



class ConversationsAdapter (private val listConversations: ArrayList<Conversations>) : RecyclerView.Adapter<ConversationsAdapter.ListViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_conversations, parent, false)
        return ListViewHolder(view)
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (room_name, deskripsi) = listConversations[position]
        holder.roomName.text = room_name
        holder.deskripsi.text = deskripsi

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listConversations[holder.adapterPosition]) }
    }
    override fun getItemCount(): Int = listConversations.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roomName: TextView = itemView.findViewById(R.id.tv_room_name)
        val deskripsi: TextView = itemView.findViewById(R.id.tv_deskripsi_room)

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Conversations)
    }


}