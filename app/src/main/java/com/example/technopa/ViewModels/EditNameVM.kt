package com.example.technopa.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technopa.models.Repository
import com.example.technopa.models.User

class EditNameVM: ViewModel() {

    var user = MutableLiveData<User?>()
    var repository = Repository()

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