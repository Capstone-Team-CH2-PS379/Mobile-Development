package com.example.fluentin.ui.podcast.adapter


import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.fluentin.R
import com.example.fluentin.data.remote.response.Channel
import com.example.fluentin.data.remote.response.Conversations
import com.example.fluentin.data.remote.response.Kategori
import com.example.fluentin.databinding.ItemMenuHomeBinding



class NameChannelAdapter (private val listChannel: ArrayList<Channel>) : RecyclerView.Adapter<NameChannelAdapter.ListViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_name_channel, parent, false)
        return ListViewHolder(view)
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, foto) = listChannel[position]
        holder.roomName.text = name
//        holder.images.setImageResource(image)
        Glide.with(holder.itemView)
            .load(foto)
            .circleCrop()
            .into(holder.images)

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listChannel[holder.adapterPosition]) }
    }
    override fun getItemCount(): Int = listChannel.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roomName: TextView = itemView.findViewById(R.id.tv_channel_names)
        val images: ImageView= itemView.findViewById(R.id.img_channel)

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Channel)
    }


}