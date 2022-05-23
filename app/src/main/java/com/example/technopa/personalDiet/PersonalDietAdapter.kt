package com.example.technopa.personalDiet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.technopa.PriemPishi
import com.example.technopa.databinding.ItemPersonalDietLayoutBinding

class PersonalDietAdapter : ListAdapter<PriemPishi, PersonalDietAdapter.Holder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemPersonalDietLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<PriemPishi>() {
        override fun areItemsTheSame(oldItem: PriemPishi, newItem: PriemPishi): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: PriemPishi, newItem: PriemPishi): Boolean {
            return oldItem == newItem
        }
    }

    class Holder(val binding: ItemPersonalDietLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PriemPishi) {
            binding.itemPersonalDietTextView.text = item.title
            // itemView.itemPersonalDietTextView.text = item.name
        }
    }
}
