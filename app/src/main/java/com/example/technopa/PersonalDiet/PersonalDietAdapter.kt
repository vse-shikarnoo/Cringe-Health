package com.example.technopa.PersonalDiet

import android.telecom.Call
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.technopa.R
import com.example.technopa.inflate
import kotlinx.android.synthetic.main.item_personal_diet_layout.view.*

//Адаптер впринципе можно не трогать, только для необходимости
class PersonalDietAdapter(
    private val onItemClick:(position:Int)  -> Unit
): ListAdapter<PriemPishi,PersonalDietAdapter.Holder>(diffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(parent.inflate(R.layout.item_personal_diet_layout),onItemClick)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    class diffUtilCallBack():DiffUtil.ItemCallback<PriemPishi>() {
        override fun areItemsTheSame(oldItem: PriemPishi, newItem: PriemPishi): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: PriemPishi, newItem: PriemPishi): Boolean {
            return oldItem == newItem
        }
    }

    class Holder(itemView: View, val onItemClick:(position:Int)  -> Unit): RecyclerView.ViewHolder(itemView){

        init {
            itemView.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        fun bind(item:PriemPishi){
            with(itemView) {
                itemPersonalDietTextView.text = item.title
                itemPersonalDietListFood.text = buildString {
                    item.contain.forEach {
                        append("${it.title} (KK${it.kkal}, Б${it.b}, Ж${it.zh}, У${it.u})\n")
                    }
                }
            }
        }
    }
}