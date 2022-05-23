package com.example.technopa.trainings.Views

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technopa.R
import com.example.technopa.Training
import com.example.technopa.autoCleared
import com.example.technopa.databinding.TrainingListLayoutBinding
import com.example.technopa.interfaces.FragmentInterface
import com.example.technopa.toast
import com.example.technopa.trainings.Models.TrainingListModel

class TrainingListFragment : Fragment(R.layout.training_list_layout) {

    private val viewModel: TrainingListModel by viewModels()

    private var binding: TrainingListLayoutBinding? = null

    private var trainingListAdapter: TrainingListAdapter by autoCleared()

    private val fragmentInterface: FragmentInterface?
        get() = activity?.let { it as? FragmentInterface }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // toast("Тренировки")
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
        trainingListAdapter = TrainingListAdapter { position ->
            fragmentInterface?.onItemSelectedTrainings(
                viewModel.trainingList.value?.get(position) ?: Training()
            )
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
        viewModel.isLoading.observe(viewLifecycleOwner) {
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
