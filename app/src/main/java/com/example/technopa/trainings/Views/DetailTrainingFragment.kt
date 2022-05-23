package com.example.technopa.trainings.Views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technopa.Classes.Training
import com.example.technopa.R

import com.example.technopa.autoCleared
import com.example.technopa.databinding.DetailTrainingLayoutBinding
import com.example.technopa.interfaces.FragmentInterface
import com.example.technopa.withArguments

class DetailTrainingFragment : Fragment(R.layout.detail_training_layout) {

    private var training: Training? = null

    private var binding: DetailTrainingLayoutBinding? = null

    private var detailExerciseAdapter : DetailTrainingExerciseListAdapter by autoCleared()

    private val fragmentInterface: FragmentInterface?
        get() = activity?.let { it as? FragmentInterface }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DetailTrainingLayoutBinding.bind(view)
        training = requireArguments().getParcelable(KEY_TRAINING)

        init()
        bindInfo(training)

        binding?.backArrowDetailTraining?.setOnClickListener {
            fragmentInterface?.openFragment(TrainingListFragment())
        }
    }

    fun init(){
        detailExerciseAdapter = DetailTrainingExerciseListAdapter()
        with(binding?.recyclerViewDetailTraining){
            this?.adapter = detailExerciseAdapter
            this?.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            this?.setHasFixedSize(true)
        }
        detailExerciseAdapter.submitList(training?.exercises)
    }

    private fun bindInfo(training: Training?) {

        binding!!.titleTextView.text = training?.title ?: ""
        binding!!.kaloriiTextView.text = "${training?.kalorii} ккал"
        binding!!.opisanieTextView.text = training?.opisanie ?: ""
    }
    companion object {

        private const val KEY_TRAINING = "key_training"

        fun newInstance(training: Training): DetailTrainingFragment {
            return DetailTrainingFragment().withArguments {
                putParcelable(KEY_TRAINING, training)
            }
        }
    }
}
