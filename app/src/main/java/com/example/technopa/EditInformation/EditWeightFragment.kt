package com.example.technopa.EditInformation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.example.technopa.R
import com.example.technopa.databinding.EditWeightLayoutBinding

class EditWeightFragment(var user: ProfileFragment.User): DialogFragment() {
    private lateinit var binding: EditWeightLayoutBinding
    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = EditWeightLayoutBinding.inflate(inflater, container,  false)

        binding.weightNp1.maxValue = 300
        binding.weightNp1.minValue = 0
        binding.weightNp1.value = user.weight.toInt()

        binding.weightNp2.maxValue = 9
        binding.weightNp2.minValue = 0
        binding.weightNp2.value = ((user.weight - user.weight.toInt())*10).toInt()

        binding.acceptButton.setOnClickListener(){
            user.weight = binding.weightNp1.value.toDouble()+(binding.weightNp2.value.toDouble()/10)
            val weight: TextView = requireActivity().findViewById(R.id.weight_value_tv)
            weight.text = user.weight.toString()

            val progressBar: ProgressBar = requireActivity().findViewById(R.id.progressBar)
            progressBar.max = user.weight.toInt()
            progressBar.setProgress(user.desired_weight.toInt(),true)

            val progressValueTv: TextView = requireActivity().findViewById(R.id.progress_value_tv)
            progressValueTv.text = (((user.desired_weight/user.weight)*100).toInt()).toString() + "%"

            dismiss()
        }

        binding.cancelButton.setOnClickListener(){
            dismiss()
        }

        return binding.root

    }
}