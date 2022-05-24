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
import com.example.technopa.profile.Models.ProfileVM
import com.example.technopa.databinding.EditWeightLayoutBinding
import com.example.technopa.profile.Repos.MainUser

class EditWeightFragment(val application: Activity): DialogFragment() {

    private val APP_PREFERENCES = R.string.APP_PREFERENCES.toString()

    private val mSettings: SharedPreferences? = application.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

    private lateinit var binding: EditWeightLayoutBinding

    private val profileVM : ProfileVM by viewModels(ownerProducer = {requireParentFragment()})

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = EditWeightLayoutBinding.inflate(inflater, container,  false)

        profileVM.user.observe(viewLifecycleOwner){
            setNumberPickers(it)
        }

        binding.acceptButton.setOnClickListener {
            val weight: String = binding.weightNp1.value.toString() + "." + binding.weightNp2.value.toString()
            val editor: SharedPreferences.Editor? = mSettings?.edit()
            editor?.putString("WEIGHT", weight)
            editor?.apply()
            profileVM.setWeight(binding.weightNp1.value, binding.weightNp2.value)
            dismiss()
        }

        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        return binding.root

    }

    private fun setNumberPickers(user: MainUser?) {
        binding.weightNp1.maxValue = 300
        binding.weightNp1.minValue = 0
        binding.weightNp1.value = user?.weight?.toInt() ?: 100

        binding.weightNp2.maxValue = 9
        binding.weightNp2.minValue = 0
        profileVM.weightNp2.observe(viewLifecycleOwner){
            binding.weightNp2.value = it
        }
    }

}