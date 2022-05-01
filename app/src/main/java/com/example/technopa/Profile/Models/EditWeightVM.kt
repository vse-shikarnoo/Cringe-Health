package com.example.technopa.Profile.Models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technopa.Profile.Repos.Repository
import com.example.technopa.Profile.Repos.MainUser

class EditWeightVM: ViewModel() {

    var user = MutableLiveData<MainUser?>()
    var weightNp2 = MutableLiveData<Int>()
    var repository = Repository()

    init {
        user.value = repository.getUser()
        weightNp2.value = ((user.value!!.weight - user.value!!.weight.toInt())*10).toInt()
    }

    fun setWeight(IntPart: Int, FracPart: Int) {
        val user1 = user.value
        user1?.weight = IntPart.toDouble()+(FracPart.toDouble()/10)
        repository.sendUser(user1)
    }
}