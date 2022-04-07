package com.example.technopa.Fragments.EditInformation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.example.technopa.ViewModels.EditNameVM
import com.example.technopa.databinding.EditNameLayoutBinding
import com.example.technopa.models.User

class EditNameFragment(var user: MutableLiveData<User>): DialogFragment(){
    private lateinit var binding: EditNameLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val editNameVM = EditNameVM()

        binding = EditNameLayoutBinding.inflate(inflater, container,  false)

        binding.nameEditingEt.setText(user.value?.name)
        binding.surnameEditingEt.setText(user.value?.surname)

        binding.acceptButton.setOnClickListener(){
            user.value = editNameVM.setNameSurname(binding.nameEditingEt.text.toString(), binding.surnameEditingEt.text.toString(), user.value)
            dismiss()
        }

        binding.cancelButton.setOnClickListener(){
            dismiss()
        }

        return binding.root

    }

}