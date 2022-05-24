package com.example.technopa.profile.Views

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.technopa.databinding.EditDialogLayoutBinding

class EditDialogFragment(val application: Activity) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = EditDialogLayoutBinding.inflate(inflater, container,  false)

        binding.editDesWeight.setOnClickListener {
            val editDesWeight = EditDesiredWeightFragment(application)
            editDesWeight.show(childFragmentManager, "EditDesWeightDialog")
        }
        binding.editWeight.setOnClickListener {
            val editWeight = EditWeightFragment(application)
            editWeight.show(childFragmentManager, "EditWeightDialog")
        }
        binding.editHeight.setOnClickListener {
            val editHeight = EditHeightFragment(application)
            editHeight.show(childFragmentManager, "EditDesHeightDialogv")
        }
        binding.editName.setOnClickListener {
            val editName = EditNameFragment(application)
            editName.show(childFragmentManager, "EditNameDialog")
        }
        binding.backButton.setOnClickListener {
            dismiss()
        }

        return binding.root

    }


}