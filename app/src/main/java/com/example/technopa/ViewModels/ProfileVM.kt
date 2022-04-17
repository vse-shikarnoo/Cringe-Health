package com.example.technopa.ViewModels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technopa.models.Repository
import com.example.technopa.models.User

class ProfileVM: ViewModel() {

        var user = MutableLiveData<User?>()

        init {
                user.value = Repository().getUser()
        }

        fun progressText(): String{
                return (((user.value!!.desired_weight / user.value!!.weight)*100).toInt()).toString() + "%"
        }
}