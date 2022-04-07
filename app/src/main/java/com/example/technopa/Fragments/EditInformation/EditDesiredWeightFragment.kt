package com.example.technopa.Fragments.EditInformation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.example.technopa.ViewModels.EditDesiredWeightVM
import com.example.technopa.databinding.EditDesWeightLayoutBinding
import com.example.technopa.models.User

class EditDesiredWeightFragment(var user: MutableLiveData<User>) : DialogFragment(){

    private lateinit var binding: EditDesWeightLayoutBinding
    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = EditDesWeightLayoutBinding.inflate(inflater, container,  false)

        val editDesWeightVM = EditDesiredWeightVM()

        setNumberPickers(user.value, editDesWeightVM)

        binding.acceptButton.setOnClickListener(){
            user.value = editDesWeightVM.setDesWeight(binding.desiredWeightNp1.value, binding.desiredWeightNp2.value, user.value)
            dismiss()
        }

        binding.cancelButton.setOnClickListener(){
            dismiss()
        }

        return binding.root

    }

    private fun setNumberPickers(user: User?, VM: EditDesiredWeightVM) {
        binding.desiredWeightNp1.maxValue = 300
        binding.desiredWeightNp1.minValue = 0
        binding.desiredWeightNp1.value = user?.desired_weight!!.toInt()

        binding.desiredWeightNp2.maxValue = 9
        binding.desiredWeightNp2.minValue = 0
        binding.desiredWeightNp2.value = VM.desiredWeightNp2(user)
    }

}


