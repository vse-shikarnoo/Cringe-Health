package com.example.technopa.trainings.Views

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.technopa.Exercise
import com.example.technopa.R
import com.example.technopa.Training
import com.example.technopa.databinding.ItemExerciseDetailTrainingLayoutBinding
import com.example.technopa.databinding.ItemTrainingLayoutBinding

class DetailTrainingExerciseListAdapter() :
    ListAdapter<Exercise, DetailTrainingExerciseListAdapter.DetailTrainingHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailTrainingHolder {
        val binding = ItemExerciseDetailTrainingLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DetailTrainingHolder(binding,parent)
    }

    override fun onBindViewHolder(holder: DetailTrainingHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Exercise>() {
        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem == newItem
        }
    }

    class DetailTrainingHolder(
        private val binding: ItemExerciseDetailTrainingLayoutBinding,
        val parent: ViewGroup
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(item: Exercise) {
            binding.itemExerciseTextView.text = item.title
            binding.itemPovtTextView.text = "Повторения : ${item.povtoreniya}"
        }
    }


}