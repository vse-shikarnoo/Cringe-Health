package com.example.technopa.Fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.example.technopa.R
import com.example.technopa.ViewModels.ProfileVM
import com.example.technopa.databinding.ProfileLayoutBinding
import com.example.technopa.models.User

class ProfileFragment: Fragment() {

    private lateinit var binding: ProfileLayoutBinding

    private val profilevm : ProfileVM by viewModels()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = ProfileLayoutBinding.inflate(inflater, container,  false)

        profilevm.user.observe(viewLifecycleOwner)  {
            setUserData(profilevm.user.value)
        }

        //change information
        binding.editTv.setOnClickListener {
            val dialog = EditDialogFragment()
            dialog.show(childFragmentManager, "EditDialog")
        }

        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun setUserData(user: User?){
        binding.nameTv.text = user?.name
        binding.surnameTv.text = user?.surname
        binding.heightValueTv.text = user?.height.toString()
        binding.weightValueTv.text = user?.weight.toString()
        binding.desiredWeightValueTv.text = user?.desired_weight.toString()
        binding.progressBar.max = user?.weight?.toInt() ?: 100
        binding.progressBar.setProgress(user?.desired_weight?.toInt() ?: 100,true)
        profilevm.progressText.observe(viewLifecycleOwner) {
            binding.progressValueTv.text = it
        }
    }
}
