package com.example.technopa.profile.Models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technopa.profile.Repos.Repository
import com.example.technopa.profile.Repos.MainUser

class EditWeightVM: ViewModel() {

    var user = MutableLiveData<MainUser?>()
    private var weightNp2 = MutableLiveData<Int>()
    private var repository = Repository()

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