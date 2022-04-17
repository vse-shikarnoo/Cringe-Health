package com.example.technopa.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.technopa.Fragments.EditInformation.EditDesiredWeightFragment
import com.example.technopa.Fragments.EditInformation.EditHeightFragment
import com.example.technopa.Fragments.EditInformation.EditWeightFragment
import com.example.technopa.Fragments.EditInformation.EditNameFragment
import com.example.technopa.databinding.EditDialogLayoutBinding

class EditDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = EditDialogLayoutBinding.inflate(inflater, container,  false)

        binding.editDesWeight.setOnClickListener {
            val editDesWeight = EditDesiredWeightFragment()
            childFragmentManager.let { it1 -> editDesWeight.show(it1, "EditDesWeightDialog") }
        }
        binding.editWeight.setOnClickListener {
            val editWeight = EditWeightFragment()
            childFragmentManager.let { it1 -> editWeight.show(it1, "EditWeightDialog") }
        }
        binding.editHeight.setOnClickListener {
            val editHeight = EditHeightFragment()
            childFragmentManager.let { it1 -> editHeight.show(it1, "EditDesHeightDialog") }
        }
        binding.editName.setOnClickListener {
            val editName = EditNameFragment()
            childFragmentManager.let { it1 -> editName.show(it1, "EditNameDialog") }
        }
        binding.backButton.setOnClickListener {
            dismiss()
        }

        return binding.root

    }


}