package com.example.technopa.trainings.Views

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.technopa.Classes.Training
import com.example.technopa.R

import com.example.technopa.databinding.ItemTrainingLayoutBinding

class TrainingListAdapter(
    private val onItemClick: (position: Int) -> Unit
) : ListAdapter<Training, TrainingListAdapter.Holder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val binding = ItemTrainingLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding, onItemClick, parent)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Training>() {
        override fun areItemsTheSame(oldItem: Training, newItem: Training): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Training, newItem: Training): Boolean {
            return oldItem == newItem
        }
    }

    class Holder(
        private val binding: ItemTrainingLayoutBinding,
        val onItemClick: (position: Int) -> Unit,
        val parent: ViewGroup
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick(position)
            }
        }

        fun bind(item: Training) {
            binding.itemTrainingTitle.text = item.title
            binding.itemTrainingKkal.text = "${item.kalorii} ккал сжигается"
            binding.itemTrainingExercises.text = parent.context.resources.getQuantityString(R.plurals.exercises, item.exercises.size, item.exercises.size)

            binding.itemTrainingTitle.animation = AnimationUtils.loadAnimation(parent.context,R.anim.animation)
            binding.itemTrainingKkal.animation = AnimationUtils.loadAnimation(parent.context,R.anim.animation)
            binding.itemTrainingExercises.animation = AnimationUtils.loadAnimation(parent.context,R.anim.animation)
            // itemView.itemPersonalDietTextView.text = item.name
        }
    }
}
