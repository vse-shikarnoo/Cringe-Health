package com.example.technopa.profile.Views


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.technopa.profile.Models.EditNameVM
import com.example.technopa.databinding.EditNameLayoutBinding

class EditNameFragment: DialogFragment(){

    private val editNameVM : EditNameVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val binding = EditNameLayoutBinding.inflate(inflater, container,  false)

        editNameVM.user.observe(viewLifecycleOwner){
            binding.nameEditingEt.setText(it?.name)
            binding.surnameEditingEt.setText(it?.surname)
        }

        binding.acceptButton.setOnClickListener {
            editNameVM.setNameSurname(binding.nameEditingEt.text.toString(), binding.surnameEditingEt.text.toString())
            dismiss()
        }

        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        return binding.root

    }

}