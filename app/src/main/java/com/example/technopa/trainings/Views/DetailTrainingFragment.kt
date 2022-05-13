package com.example.technopa.trainings.Views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.technopa.R
import com.example.technopa.Training
import com.example.technopa.databinding.DetailTrainingLayoutBinding
import com.example.technopa.withArguments

class DetailTrainingFragment: Fragment(R.layout.detail_training_layout) {



    private var training: Training? = null

    private var binding: DetailTrainingLayoutBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DetailTrainingLayoutBinding.bind(view)

        bindInfo()

    }

    private fun bindInfo(){
        training = requireArguments().getParcelable(KEY_TRAINING)
        binding!!.titleTextView.text = training?.title?:""
        binding!!.kaloriiTextView.text = "${training?.kalorii} ккал"
        binding!!.opisanieTextView.text = training?.opisanie?:""
    }
    companion object {

        private const val KEY_TRAINING = "key_training"

        fun newInstance(training: Training): DetailTrainingFragment {
            return DetailTrainingFragment().withArguments {
                putParcelable(KEY_TRAINING,training)
            }
        }
    }
}