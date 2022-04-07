package com.example.technopa.Fragments.EditInformation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.example.technopa.ViewModels.EditHeightVM
import com.example.technopa.databinding.EditHeightLayoutBinding
import com.example.technopa.models.User

class EditHeightFragment(var user: MutableLiveData<User>): DialogFragment() {
    private lateinit var binding: EditHeightLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = EditHeightLayoutBinding.inflate(inflater, container,  false)

        val editHeightVM = EditHeightVM()

        setNumberPicker(user.value)

        binding.acceptButton.setOnClickListener(){
            user.value = editHeightVM.setHeight(binding.heightNp1.value, user.value)
            dismiss()
        }

        binding.cancelButton.setOnClickListener(){
            dismiss()
        }

        return binding.root
    }

    private fun setNumberPicker(user: User?){
        binding.heightNp1.maxValue = 300
        binding.heightNp1.minValue = 0
        binding.heightNp1.value = user!!.height
    }
}