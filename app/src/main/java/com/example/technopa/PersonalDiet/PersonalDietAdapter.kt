package com.example.technopa.PersonalDiet

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.technopa.R
import com.example.technopa.inflate
import kotlinx.android.synthetic.main.item_personal_diet_layout.view.*

class PersonalDietAdapter: ListAdapter<PriemPishi,PersonalDietAdapter.Holder>(diffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(parent.inflate(R.layout.item_personal_diet_layout))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    class diffUtilCallBack():DiffUtil.ItemCallback<PriemPishi>() {
        override fun areItemsTheSame(oldItem: PriemPishi, newItem: PriemPishi): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: PriemPishi, newItem: PriemPishi): Boolean {
            return oldItem == newItem
        }
    }

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item:PriemPishi){
            itemView.itemPersonalDietTextView.text = item.name
        }
    }
}