package com.example.fluentin.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.fluentin.R
import com.example.fluentin.data.remote.response.Kategori
import com.example.fluentin.databinding.ItemMenuHomeBinding



class KategoriAdapter (private val listKategori: ArrayList<Kategori>) : RecyclerView.Adapter<KategoriAdapter.ListViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_menu_home, parent, false)
        return ListViewHolder(view)
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (kategori_name) = listKategori[position]
        holder.tvKategori.text = kategori_name

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listKategori[holder.adapterPosition]) }
    }
    override fun getItemCount(): Int = listKategori.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvKategori: TextView = itemView.findViewById(R.id.tv_menu_home)

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Kategori)
    }


}