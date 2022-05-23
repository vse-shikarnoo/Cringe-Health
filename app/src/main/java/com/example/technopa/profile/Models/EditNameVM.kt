package com.example.technopa.profile.Models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technopa.profile.Repos.Repository
import com.example.technopa.profile.Repos.MainUser

class EditNameVM: ViewModel() {

    var user = MutableLiveData<MainUser?>()
    private var repository = Repository()

    init {
        user.value = repository.getUser()
    }

    fun setNameSurname(newName: String, newSurname: String) {
        val user1 = user.value
        user1?.name = newName
        user1?.surname = newSurname
        repository.sendUser(user1)
    }


}