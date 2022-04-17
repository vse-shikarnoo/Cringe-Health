package com.example.technopa.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technopa.models.Repository
import com.example.technopa.models.User

class EditHeightVM: ViewModel() {

    var user = MutableLiveData<User?>()

    init {
        user.value = Repository().getUser()
    }

    fun setHeight(newHeight: Int) {
        val user1 = user.value!!
        user1.height = newHeight
        Repository().sendUser(user1)
    }
}