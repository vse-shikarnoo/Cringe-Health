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
import com.example.technopa.databinding.AchievmentBinding


class AchieveAdapter(
    private val achievments: List<String>,
    private val achievmentString: String
) : RecyclerView.Adapter<AchieveAdapter.MainViewHolder>() {
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        /*val view = LayoutInflater.from(parent.context).inflate(R.layout.achievment, null)
        return MainViewHolder(view)

         */

        val binding = AchievmentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(achievments[position], achievmentString[position] != '0')
    }

    override fun getItemCount(): Int {
        return achievments.size
    }

    class MainViewHolder(private val binding: AchievmentBinding) : RecyclerView.ViewHolder(binding.root) {





        fun bind(title: String, starFlag: Boolean) {
            //ivPoster.setImageResource(R.drawable.btn)

            binding.achieveTitle.text = title
            if (starFlag==false){
                binding.achieveImageViewFalse.visibility = View.VISIBLE
                binding.achieveImageViewTrue.visibility = View.GONE
            }else{
                binding.achieveImageViewFalse.visibility = View.GONE
                binding.achieveImageViewTrue.visibility = View.VISIBLE
            }
        }
    }

}