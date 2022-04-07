package com.example.technopa.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technopa.models.User

class EditNameVM: ViewModel() {

    fun setNameSurname(newName: String, newSurname: String, user: User?): User {
        var user1 = user!!
        user1.name = newName
        user1.surname = newSurname
        return user1
        // + send to server
    }


}