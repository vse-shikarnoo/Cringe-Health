package com.example.technopa.profile.Views


import android.annotation.SuppressLint
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
import androidx.lifecycle.AndroidViewModel
import com.example.technopa.R
import com.example.technopa.profile.Models.EditNameVM
import com.example.technopa.databinding.EditNameLayoutBinding

class EditNameFragment(application: Activity): DialogFragment(){

    private val editNameVM : EditNameVM by viewModels()

    private val APP_PREFERENCES = R.string.APP_PREFERENCES.toString()

    private val mSettings: SharedPreferences? = application.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

    @SuppressLint("CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val binding = EditNameLayoutBinding.inflate(inflater, container,  false)
        editNameVM.user.observe(viewLifecycleOwner){
            binding.nameEditingEt.setText(it?.name)
            binding.surnameEditingEt.setText(it?.surname)
        }

        binding.acceptButton.setOnClickListener {
            val editor: SharedPreferences.Editor? = mSettings?.edit()
            editor?.putString("NAME", binding.nameEditingEt.text.toString())
            editor?.putString("SURNAME", binding.surnameEditingEt.text.toString())
            editor?.apply()
            editNameVM.setNameSurname(binding.nameEditingEt.text.toString(), binding.surnameEditingEt.text.toString())
            dismiss()
        }

        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        return binding.root

    }

}
