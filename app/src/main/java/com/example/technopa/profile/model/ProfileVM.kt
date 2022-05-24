package com.example.technopa.profile.Models


import android.app.Activity
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technopa.profile.Repos.Repository
import com.example.technopa.profile.Repos.MainUser

class ProfileVM(): ViewModel() {

        val user = MutableLiveData<MainUser?>()
        var progressText = MutableLiveData<String>()
        var weightNp2 = MutableLiveData<Int>()
        private var repository = Repository()
        private var mSettings: SharedPreferences? = null

        init {
                user.value = repository.getUser()
                progressText.value = (((user.value?.weight?.let { user.value?.desired_weight?.div(it) })?.times(
                        100
                ))?.toInt()).toString() + "%"
                weightNp2.value = ((user.value!!.weight - user.value!!.weight.toInt())*10).toInt()
        }

        fun setWeight(IntPart: Int, FracPart: Int) {
                val user1 = user.value
                user1?.weight = IntPart.toDouble()+(FracPart.toDouble()/10)
                user.value = user1
                repository.sendUser(user1)
        }



}