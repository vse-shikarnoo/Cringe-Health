package com.example.technopa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.technopa.EditInformation.EditDesiredWeightFragment
import com.example.technopa.EditInformation.EditHeightFragment
import com.example.technopa.EditInformation.EditNameFragment
import com.example.technopa.EditInformation.EditWeightFragment
import com.example.technopa.databinding.ActivityMainBinding.inflate
import com.example.technopa.databinding.EditDialogLayoutBinding
import com.example.technopa.databinding.ProfileLayoutBinding
import kotlinx.android.synthetic.main.edit_dialog_layout.*
import kotlinx.android.synthetic.main.profile_layout.*

class EditDialogFragment(var user : ProfileFragment.User) : DialogFragment() {
    private lateinit var binding: EditDialogLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = EditDialogLayoutBinding.inflate(inflater, container,  false)


        binding.editDesWeight.setOnClickListener(){
            val editDesWeight = EditDesiredWeightFragment(user)
            childFragmentManager.let { it1 -> editDesWeight.show(it1, "EditDesWeightDialog") }
        }
        binding.editWeight.setOnClickListener(){
            val editWeight = EditWeightFragment(user)
            childFragmentManager.let { it1 -> editWeight.show(it1, "EditWeightDialog") }
        }
        binding.editHeight.setOnClickListener(){
            val editHeight = EditHeightFragment(user)
            childFragmentManager.let { it1 -> editHeight.show(it1, "EditDesHeightDialog") }
        }
        binding.editName.setOnClickListener(){
            val editName = EditNameFragment(user)
            childFragmentManager.let { it1 -> editName.show(it1, "EditNameDialog") }
        }
        binding.backButton.setOnClickListener(){
            dismiss()
        }

        return binding.root

    }


}