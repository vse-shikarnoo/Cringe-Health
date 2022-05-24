package com.example.technopa.Profile.Models

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.technopa.R


class AchieveAdapter(private val achievments: List<String>) : RecyclerView.Adapter<AchieveAdapter.MainViewHolder>() {
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.achievment, null)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(achievments[position])
    }

    override fun getItemCount(): Int {
        return achievments.size
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPoster: ImageView = itemView.findViewById(R.id.achieve_imageView)
        val tvTitle: TextView = itemView.findViewById(R.id.achieve_title)




        fun bind(title: String) {
            //ivPoster.setImageResource(R.drawable.btn)
            tvTitle.text = title
        }
    }

}