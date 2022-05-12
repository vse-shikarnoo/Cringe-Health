package com.example.technopa.Profile.Views

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.technopa.Profile.Models.EatAdapter
import com.example.technopa.Profile.Models.ProfileVM
import com.example.technopa.databinding.ProfileLayoutBinding
import com.example.technopa.Profile.Repos.MainUser

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

        binding = ProfileLayoutBinding.inflate(inflater, container,  false)

        profilevm.user.observe(this, Observer { setUserData(it) })

        //change information
        binding.editTv.setOnClickListener {
            EditDialogFragment().show(childFragmentManager, "EditDialog")
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
        binding.priemyPishyRv.adapter = EatAdapter(profilevm.user.value!!.eating)
        binding.priemyPishyRv.layoutManager = LinearLayoutManager(context)
    }




}
