package com.example.technopa.ViewModels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technopa.models.Repository
import com.example.technopa.models.User

class ProfileVM: ViewModel() {

        var user = MutableLiveData<User?>()
        var progressText = MutableLiveData<String>()

        init {
                user.value = Repository().getUser()
                progressText.value = (((user.value?.weight?.let { user.value?.desired_weight?.div(it) })?.times(
                        100
                ))?.toInt()).toString() + "%"
        }

}