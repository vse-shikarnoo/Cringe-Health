package com.example.technopa.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technopa.Fragments.ProfileFragment
import com.example.technopa.models.Repository
import com.example.technopa.models.User

class EditDesiredWeightVM: ViewModel() {

    var userLV = MutableLiveData<User?>()
    var desiredWeightNp2 = MutableLiveData<Int>()
    var repository = Repository()

    val user:LiveData<User?>
        get() = userLV

    init {
        userLV.value = repository.getUser()
        desiredWeightNp2.value = ((user.value!!.desired_weight - user.value!!.desired_weight.toInt())*10).toInt()
    }

    fun setDesWeight(IntPart: Int, FractionalPart: Int) {
        val user1 = user.value
        user1?.desired_weight = IntPart.toDouble()+(FractionalPart.toDouble()/10)
        repository.sendUser(user1)
    }
}