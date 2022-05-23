package com.example.technopa.profile.Models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technopa.profile.Repos.MainUser
import com.example.technopa.profile.Repos.Repository

class EditHeightVM : ViewModel() {

    var user = MutableLiveData<MainUser?>()
    private var repository = Repository()

    init {
        user.value = repository.getUser()
    }

    fun setHeight(newHeight: Int) {
        val user1 = user.value
        user1?.height = newHeight
        repository.sendUser(user1)
    }
}
