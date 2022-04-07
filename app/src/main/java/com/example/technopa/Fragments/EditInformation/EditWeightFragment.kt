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
import com.example.technopa.ViewModels.EditWeightVM
import com.example.technopa.databinding.EditWeightLayoutBinding
import com.example.technopa.models.User

class EditWeightFragment(var user: MutableLiveData<User>): DialogFragment() {

    private lateinit var binding: EditWeightLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = EditWeightLayoutBinding.inflate(inflater, container,  false)

        val editWeightVM = EditWeightVM()

        setNumberPickers(user.value, editWeightVM)

        binding.acceptButton.setOnClickListener(){
            user.value = editWeightVM.setWeight(binding.weightNp1.value, binding.weightNp2.value, user.value)
            dismiss()
        }

        binding.cancelButton.setOnClickListener(){
            dismiss()
        }

        return binding.root

    }

    private fun setNumberPickers(user: User?, VM: EditWeightVM) {
        binding.weightNp1.maxValue = 300
        binding.weightNp1.minValue = 0
        binding.weightNp1.value = user?.weight!!.toInt()

        binding.weightNp2.maxValue = 9
        binding.weightNp2.minValue = 0
        binding.weightNp2.value = VM.weightNp2(user)
    }

}