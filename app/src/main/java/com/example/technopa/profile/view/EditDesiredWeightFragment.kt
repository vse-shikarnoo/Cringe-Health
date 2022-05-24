package com.example.technopa.profile.Views

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.technopa.R
import com.example.technopa.profile.Models.EditDesiredWeightVM
import com.example.technopa.databinding.EditDesWeightLayoutBinding
import com.example.technopa.profile.Repos.MainUser

class EditDesiredWeightFragment(val application: Activity) : DialogFragment(){

    private val APP_PREFERENCES = R.string.APP_PREFERENCES.toString()

    private val mSettings: SharedPreferences? = application.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

    private val editDesWeightVM : EditDesiredWeightVM by viewModels()

    private lateinit var binding: EditDesWeightLayoutBinding
    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = EditDesWeightLayoutBinding.inflate(inflater, container,  false)

        editDesWeightVM.user.observe(viewLifecycleOwner){
            setNumberPickers(it)
        }

        binding.acceptButton.setOnClickListener {
            val des_weight: String = binding.desiredWeightNp1.value.toString() + "." + binding.desiredWeightNp2.value.toString()
            val editor: SharedPreferences.Editor? = mSettings?.edit()
            editor?.putString("DES_WEIGHT", des_weight)
            editor?.apply()
            editDesWeightVM.setDesWeight(binding.desiredWeightNp1.value, binding.desiredWeightNp2.value)
            dismiss()
        }

        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        return binding.root

    }

    private fun setNumberPickers(user: MainUser?) {
        binding.desiredWeightNp1.maxValue = 300
        binding.desiredWeightNp1.minValue = 0
        binding.desiredWeightNp1.value = user?.desired_weight?.toInt() ?: 0

        binding.desiredWeightNp2.maxValue = 9
        binding.desiredWeightNp2.minValue = 0
        editDesWeightVM.desiredWeightNp2.observe(viewLifecycleOwner){
            binding.desiredWeightNp2.value = it
        }
    }

}


