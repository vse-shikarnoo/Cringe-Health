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
import com.example.technopa.databinding.EditDesWeightLayoutBinding
import com.example.technopa.databinding.EditNameLayoutBinding

class EditDesiredWeightFragment(var user: ProfileFragment.User) : DialogFragment(){

    private lateinit var binding: EditDesWeightLayoutBinding
    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = EditDesWeightLayoutBinding.inflate(inflater, container,  false)

        binding.desiredWeightNp1.maxValue = 300
        binding.desiredWeightNp1.minValue = 0
        binding.desiredWeightNp1.value = user.desired_weight.toInt()


        binding.desiredWeightNp2.maxValue = 9
        binding.desiredWeightNp2.minValue = 0
        binding.desiredWeightNp2.value = ((user.desired_weight - user.desired_weight.toInt())*10).toInt()




        binding.acceptButton.setOnClickListener(){
            user.desired_weight = binding.desiredWeightNp1.value.toDouble()+(binding.desiredWeightNp2.value.toDouble()/10)
            val desiredWeight: TextView = requireActivity().findViewById(R.id.desired_weight_value_tv)
            desiredWeight.text = user.desired_weight.toString()

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


