package com.example.technopa.ViewModels

import androidx.lifecycle.ViewModel
import com.example.technopa.models.User

class EditDesiredWeightVM: ViewModel() {
    fun desiredWeightNp2(user: User): Int {
        return ((user.desired_weight - user.desired_weight.toInt())*10).toInt()
    }

    fun setDesWeight(IntPart: Int, FracPart: Int, user: User?): User {
        var user1 = user!!
        user1.desired_weight = IntPart.toDouble()+(FracPart.toDouble()/10)
        return user1
        // + send to server
    }
}