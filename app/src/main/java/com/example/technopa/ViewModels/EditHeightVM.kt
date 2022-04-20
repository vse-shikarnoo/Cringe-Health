package com.example.technopa.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technopa.models.Repository
import com.example.technopa.models.User

class EditHeightVM: ViewModel() {

    var user = MutableLiveData<User?>()
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