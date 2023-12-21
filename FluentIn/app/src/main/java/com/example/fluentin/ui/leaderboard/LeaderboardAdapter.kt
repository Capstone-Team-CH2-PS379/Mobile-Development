package com.example.fluentin.ui.leaderboard

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fluentin.R
import com.example.fluentin.data.remote.response.LeaderboardData
import com.example.fluentin.data.remote.response.LeaderboardItem
import com.example.fluentin.databinding.ItemLeaderboardBinding
import com.example.fluentin.ui.detailleaderboard.DetailLeaderboardActivity

class LeaderboardAdapter : RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>(){

    private val leaderboardList = ArrayList<LeaderboardData>()
    private var onItemClickCallback : OnItemClickCallback? = null

    fun setAllLeaderboardList(packageResponse: List<LeaderboardData>){
        leaderboardList.clear()
        leaderboardList.addAll(packageResponse.sortedByDescending { it.skor.toInt() })
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemLeaderboardBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(allLeaderboard: LeaderboardData){
            with(binding) {
                tvChannelName.text = allLeaderboard.full_name
                tvPosisiLeaderboard.text = "#${adapterPosition + 1}"

                val position = adapterPosition + 1

                when (position) {
                    1 -> imgPiala.setImageResource(R.drawable.icon_piala_satu)
                    2 -> imgPiala.setImageResource(R.drawable.icon_piala_dua)
                    3 -> imgPiala.setImageResource(R.drawable.icon_piala_tiga)
                    else -> imgPiala.visibility = View.GONE
                }
            }
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, DetailLeaderboardActivity::class.java)
                intent.putExtra(DetailLeaderboardActivity.EXTRA_ID, allLeaderboard.user_id.toString())
                intent.putExtra(DetailLeaderboardActivity.EXTRA_NAME, allLeaderboard.full_name.toString())
                intent.putExtra(DetailLeaderboardActivity.EXTRA_POINTS, allLeaderboard.skor.toString())
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LeaderboardAdapter.ViewHolder {
        val view = ItemLeaderboardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: LeaderboardAdapter.ViewHolder, position: Int) {
        holder.bind(leaderboardList[position])
    }

    override fun getItemCount(): Int =leaderboardList.size

    interface OnItemClickCallback{
        fun onItemClicked(packageResponse: LeaderboardData)
    }

}