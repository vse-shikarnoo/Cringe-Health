package com.example.technopa.profile.Views

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.technopa.databinding.EditDialogLayoutBinding
import com.example.technopa.profile.Models.ProfileVM

class EditDialogFragment(val application: Activity, var profilevm: ProfileVM) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = EditDialogLayoutBinding.inflate(inflater, container,  false)

        binding.editDesWeight.setOnClickListener {
            val editDesWeight = EditDesiredWeightFragment(application, profilevm)
            editDesWeight.show(childFragmentManager, "EditDesWeightDialog")
        }
        binding.editWeight.setOnClickListener {
            val editWeight = EditWeightFragment(application, profilevm)
            editWeight.show(childFragmentManager, "EditWeightDialog")
        }
        binding.editHeight.setOnClickListener {
            val editHeight = EditHeightFragment(application, profilevm)
            editHeight.show(childFragmentManager, "EditDesHeightDialogv")
        }
        binding.editName.setOnClickListener {
            val editName = EditNameFragment(application, profilevm)
            editName.show(childFragmentManager, "EditNameDialog")
        }
        binding.backButton.setOnClickListener {
            dismiss()
        }

        return binding.root

    }


}