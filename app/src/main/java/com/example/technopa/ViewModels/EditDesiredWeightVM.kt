package com.example.technopa.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technopa.models.Repository
import com.example.technopa.models.User

class EditDesiredWeightVM: ViewModel() {

    var user = MutableLiveData<User?>()

    init {
        user.value = Repository().getUser()
    }

    fun desiredWeightNp2(): Int {
        return ((user.value!!.desired_weight - user.value!!.desired_weight.toInt())*10).toInt()
    }

    fun setDesWeight(IntPart: Int, FracPart: Int) {
        val user1 = user.value!!
        user1.desired_weight = IntPart.toDouble()+(FracPart.toDouble()/10)
        Repository().sendUser(user1)
    }
}