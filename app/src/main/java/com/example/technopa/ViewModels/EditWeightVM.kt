package com.example.technopa.ViewModels

import androidx.lifecycle.ViewModel
import com.example.technopa.models.User

class EditWeightVM: ViewModel() {
    fun weightNp2(user: User): Int {
        return ((user.weight - user.weight.toInt())*10).toInt()
    }

    fun setWeight(IntPart: Int, FracPart: Int, user: User?): User {
        var user1 = user!!
        user1.weight = IntPart.toDouble()+(FracPart.toDouble()/10)
        return user1
        // + send to server
    }
}