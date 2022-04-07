package com.example.technopa.ViewModels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technopa.models.User

class ProfileVM: ViewModel() {

        var user = MutableLiveData<User>()

        fun progressText(user: User): String{
                return (((user.desired_weight / user.weight)*100).toInt()).toString() + "%"
        }
}