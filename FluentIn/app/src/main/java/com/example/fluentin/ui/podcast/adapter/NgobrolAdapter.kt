package com.example.fluentin.ui.podcast.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fluentin.R
import com.example.fluentin.data.remote.response.NgobrolBareng

class NgobrolAdapter (private val listNgobrol: ArrayList<NgobrolBareng>) : RecyclerView.Adapter<NgobrolAdapter.ListViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_ngobrol_bareng, parent, false)
        return ListViewHolder(view)
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title, image) = listNgobrol[position]
        holder.tvTitle.text = title
//        holder.images.setImageResource(image)
        Glide.with(holder.itemView)
            .load(image)
            .circleCrop()
            .into(holder.images)

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listNgobrol[holder.adapterPosition]) }
    }
    override fun getItemCount(): Int = listNgobrol.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_name_ngobrol)
        val images: ImageView= itemView.findViewById(R.id.image_ngobrol)

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: NgobrolBareng)
    }


}