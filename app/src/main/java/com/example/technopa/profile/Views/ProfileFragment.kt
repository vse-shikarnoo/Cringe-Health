package com.example.technopa.profile.Views

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.technopa.FirebaseNetwork
import com.example.technopa.Profile.Views.AchieveFragment
import com.example.technopa.R
import com.example.technopa.databinding.ProfileLayoutBinding
import com.example.technopa.profile.Models.ProfileVM
import com.example.technopa.profile.Repos.MainUser

class ProfileFragment: Fragment() {

    private lateinit var binding: ProfileLayoutBinding

    private val profilevm : ProfileVM by viewModels()

    @SuppressLint("FragmentLiveDataObserve")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        FirebaseNetwork().getUser({},{},"0")
        FirebaseNetwork().getUser({},{},"1")
        FirebaseNetwork().getUser({},{},"2")
        FirebaseNetwork().getUser({},{},"3")

        binding = ProfileLayoutBinding.inflate(inflater, container,  false)

        profilevm.user.observe(this, Observer { setUserData(it) })

        //change information
        binding.editTv.setOnClickListener {
            EditDialogFragment().show(childFragmentManager, "EditDialog")
        }

        binding.achieveBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, AchieveFragment())
                .addToBackStack(AchieveFragment().toString())
                .commit()
             }


        return binding.root
    }




    @RequiresApi(Build.VERSION_CODES.N)
    private fun setUserData(user: MainUser?){
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
