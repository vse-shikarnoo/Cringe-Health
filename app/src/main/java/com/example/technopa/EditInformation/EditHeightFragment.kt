package com.example.technopa.EditInformation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.technopa.R
import com.example.technopa.databinding.EditHeightLayoutBinding

class EditHeightFragment(var user: ProfileFragment.User): DialogFragment() {
    private lateinit var binding: EditHeightLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = EditHeightLayoutBinding.inflate(inflater, container,  false)

        binding.heightNp1.maxValue = 300
        binding.heightNp1.minValue = 0
        binding.heightNp1.value = user.height


        binding.acceptButton.setOnClickListener(){
            user.height = binding.heightNp1.value
            val height: TextView = requireActivity().findViewById(R.id.height_value_tv)
            height.text = user.height.toString()
            dismiss()
        }

        binding.cancelButton.setOnClickListener(){
            dismiss()
        }

        return binding.root

    }
}