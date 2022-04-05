package com.example.technopa.EditInformation


import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.technopa.R
import com.example.technopa.databinding.EditDialogLayoutBinding
import com.example.technopa.databinding.EditNameLayoutBinding
import kotlinx.android.synthetic.main.profile_layout.*

class EditNameFragment(var user: ProfileFragment.User): DialogFragment(){
    private lateinit var binding: EditNameLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = EditNameLayoutBinding.inflate(inflater, container,  false)

        binding.nameEditingEt.setText(user.name)
        binding.surnameEditingEt.setText(user.surname)

        binding.acceptButton.setOnClickListener(){
            user.name = binding.nameEditingEt.text.toString()
            user.surname = binding.surnameEditingEt.text.toString()
            val name: TextView = requireActivity().findViewById(R.id.name_tv)
            val surname: TextView = requireActivity().findViewById(R.id.surname_tv)
            name.text = user.name
            surname.text = user.surname
            dismiss()
        }

        binding.cancelButton.setOnClickListener(){
            dismiss()
        }

        return binding.root

    }

}