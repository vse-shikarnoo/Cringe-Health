package com.example.technopa.profile.Views

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.technopa.FirebaseNetwork
import com.example.technopa.Profile.Views.AchieveFragment
import com.example.technopa.R
import com.example.technopa.databinding.ProfileLayoutBinding
import com.example.technopa.profile.Models.ProfileVM
import com.example.technopa.profile.Repos.MainUser
import java.io.File
import java.io.InputStream


class ProfileFragment(val application: Activity): Fragment() {

    private lateinit var binding: ProfileLayoutBinding

    private val profilevm : ProfileVM by viewModels()

    private val APP_PREFERENCES = R.string.APP_PREFERENCES.toString()

    @SuppressLint("UseRequireInsteadOfGet")
    private val mSettings: SharedPreferences? = application.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

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
            EditDialogFragment(application).show(childFragmentManager, "EditDialog")
        }

        binding.achieveBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, AchieveFragment())
                .addToBackStack(AchieveFragment().toString())
                .commit()
        }

        binding.profileImage.setOnClickListener{
            pickImageGallery()
        }


        return binding.root
    }



    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            binding.profileImage.setImageURI(data?.data)
            var file = File(data?.data?.path)
            val editor: SharedPreferences.Editor? = mSettings?.edit()
            editor?.putString("IMAGE_PATH", file.absolutePath)
            editor?.apply()
        }

    }


    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun setUserData(user: MainUser?){

        if (mSettings?.getString("NAME", "") != "") binding.nameTv.text = mSettings?.getString("NAME", "")
        else binding.nameTv.text = user?.name

        if (mSettings?.getString("SURNAME", "") != "") binding.surnameTv.text = mSettings?.getString("SURNAME", "")
        else binding.surnameTv.text = user?.surname

        if (mSettings?.getString("HEIGHT", "") != "") binding.heightValueTv.text = mSettings?.getString("HEIGHT", "")
        else binding.heightValueTv.text = user?.height.toString()

        if (mSettings?.getString("WEIGHT", "") != "") {
            binding.weightValueTv.text = mSettings?.getString("WEIGHT", "")
            binding.progressBar.max = mSettings?.getString("WEIGHT", "")?.split(".")?.get(0)?.toInt() ?: 100
        }
        else {
            binding.weightValueTv.text = user?.weight.toString()
            binding.progressBar.max = user?.weight?.toInt() ?: 100
        }

        if (mSettings?.getString("DES_WEIGHT", "") != "") {
            binding.desiredWeightValueTv.text = mSettings?.getString("DES_WEIGHT", "")
            binding.progressBar.progress =
                mSettings?.getString("DES_WEIGHT", "")?.split(".")?.get(0)?.toInt() ?: 70
        }
        else {
            binding.desiredWeightValueTv.text = user?.desired_weight.toString()
            binding.progressBar.progress = user?.desired_weight?.toInt() ?: 100
        }

        if (mSettings?.getString("DES_WEIGHT", "") != "" && mSettings?.getString("WEIGHT", "") != "") binding.progressValueTv.text =
            (mSettings?.getString("DES_WEIGHT", "")!!.toDouble()/
                    mSettings.getString("WEIGHT", "")!!.toDouble()*100).toInt().toString() + "%"
        else binding.progressValueTv.text = profilevm.progressText.value.toString()

    }

    companion object{
        val IMAGE_REQUEST_CODE = 1
    }

}
