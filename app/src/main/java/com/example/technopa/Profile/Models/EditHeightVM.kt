package com.example.technopa.Profile.Models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technopa.Profile.Repos.Repository
import com.example.technopa.Profile.Repos.MainUser

class EditHeightVM: ViewModel() {

    var user = MutableLiveData<MainUser?>()
    var repository = Repository()

    init {
        user.value = repository.getUser()
    }

    fun setHeight(newHeight: Int) {
        val user1 = user.value
        user1?.height = newHeight
        repository.sendUser(user1)

    }
}