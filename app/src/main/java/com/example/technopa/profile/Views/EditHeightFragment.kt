package com.example.technopa.profile.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.technopa.profile.Models.EditHeightVM
import com.example.technopa.databinding.EditHeightLayoutBinding

class EditHeightFragment: DialogFragment() {

    private val editHeightVM : EditHeightVM by viewModels()

    private lateinit var binding: EditHeightLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = EditHeightLayoutBinding.inflate(inflater, container,  false)

        setNumberPicker()

        editHeightVM.user.observe(viewLifecycleOwner){
            setNumberPicker()
        }

        binding.acceptButton.setOnClickListener {
            editHeightVM.setHeight(binding.heightNp1.value)
            dismiss()
        }

        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    private fun setNumberPicker(){
        binding.heightNp1.maxValue = 300
        binding.heightNp1.minValue = 0
        binding.heightNp1.value = editHeightVM.user.value?.height as Int
    }
}