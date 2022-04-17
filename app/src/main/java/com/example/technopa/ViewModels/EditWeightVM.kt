package com.example.technopa.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technopa.models.Repository
import com.example.technopa.models.User

class EditWeightVM: ViewModel() {

    var user = MutableLiveData<User?>()

    init {
        user.value = Repository().getUser()
    }

    fun weightNp2(): Int {
        return ((user.value!!.weight - user.value!!.weight.toInt())*10).toInt()
    }

    fun setWeight(IntPart: Int, FracPart: Int) {
        val user1 = user.value!!
        user1.weight = IntPart.toDouble()+(FracPart.toDouble()/10)
        Repository().sendUser(user1)
    }
}