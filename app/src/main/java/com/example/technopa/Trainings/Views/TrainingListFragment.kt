package com.example.technopa.Trainings.Views

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technopa.R
import com.example.technopa.Trainings.Models.TrainingListModel
import com.example.technopa.autoCleared
import com.example.technopa.databinding.TrainingListLayoutBinding
import com.example.technopa.toast

class TrainingListFragment: Fragment(R.layout.training_list_layout) {

    private val viewModel: TrainingListModel by viewModels()

    private var binding: TrainingListLayoutBinding? = null

    private var trainingListAdapter: TrainingListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //toast("Тренировки")
        super.onViewCreated(view, savedInstanceState)
        binding = TrainingListLayoutBinding.bind(view)

        init()
        observe()
        binding!!.swipeRefresh.setOnRefreshListener {
            viewModel.getTrainings()
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    fun init() {
        trainingListAdapter = TrainingListAdapter(){position ->
            val action = TrainingListFragmentDirections.actionTrainingListFragmentToDetailTrainingFragment(viewModel.trainingList.value?.get(position))
            findNavController().navigate(action)
        }
        with(binding!!.recyclerViewTrainingList) {
            adapter = trainingListAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        viewModel.getTrainings()
    }

    private fun observe() {
        viewModel.trainingList.observe(viewLifecycleOwner) { trainingListAdapter.submitList(it) }
        viewModel.isLoading.observe(viewLifecycleOwner){
            updateLoadingState(it)
            binding!!.swipeRefresh.isRefreshing = false
        }
        viewModel.isError.observe(viewLifecycleOwner) { updateErrorState(it) }

    }

    private fun updateLoadingState(isLoading: Boolean) {
        binding!!.recyclerViewTrainingList.isVisible = isLoading.not()
        binding!!.progressBar.isVisible = isLoading
    }

    private fun updateErrorState(error: Throwable?) {
        if (error != null) {
            toast(error.message.toString())
        }
    }

}