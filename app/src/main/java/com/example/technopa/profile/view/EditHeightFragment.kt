package com.example.technopa.profile.Views

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.technopa.R
import com.example.technopa.profile.Models.EditHeightVM
import com.example.technopa.databinding.EditHeightLayoutBinding

class EditHeightFragment(val application: Activity): DialogFragment() {

    private val APP_PREFERENCES = R.string.APP_PREFERENCES.toString()

    private val mSettings: SharedPreferences? = application.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)


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
            val editor: SharedPreferences.Editor? = mSettings?.edit()
            editor?.putString("HEIGHT", binding.heightNp1.value.toString())
            editor?.apply()
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