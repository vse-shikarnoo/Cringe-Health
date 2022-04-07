package com.example.technopa.Fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.technopa.ViewModels.ProfileVM
import com.example.technopa.databinding.ProfileLayoutBinding
import com.example.technopa.models.User

class ProfileFragment: Fragment() {
    private lateinit var binding: ProfileLayoutBinding

    var profilevm = ProfileVM()

    var user1 = User()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ProfileLayoutBinding.inflate(inflater, container,  false)

        profilevm.user.value = user1

        setUserData(profilevm.user.value)

        profilevm.user.observe(viewLifecycleOwner, Observer {
            setUserData(it)
        })

        //change information
        binding.editTv.setOnClickListener(){
            val dialog = EditDialogFragment(profilevm.user)
            childFragmentManager.let { it1 -> dialog.show(it1, "EditDialog") }
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
        binding.progressBar.max = user?.weight!!.toInt()
        binding.progressBar.setProgress(user.desired_weight.toInt(),true)
        binding.progressValueTv.text = profilevm.progressText(user)
    }
}