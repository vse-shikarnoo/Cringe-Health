package com.example.technopa.Profile.Models
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.technopa.Profile.Repos.item
import com.example.technopa.R

class EatAdapter(
    private val items: List<item>,
) : RecyclerView.Adapter<EatAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, null)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.item_title)
        val tvValue: TextView = itemView.findViewById(R.id.item_value)

        fun bind(item: item) {
            tvTitle.text = item.title
            tvValue.text = item.value.toString()
        }
    }
}
