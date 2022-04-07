package com.example.technopa.ViewModels

import androidx.lifecycle.ViewModel
import com.example.technopa.models.User

class EditHeightVM: ViewModel() {

    fun setHeight(newHeight: Int, user: User?): User {
        var user1 = user!!
        user1.height = newHeight
        return user1
        // + send to server
    }
}