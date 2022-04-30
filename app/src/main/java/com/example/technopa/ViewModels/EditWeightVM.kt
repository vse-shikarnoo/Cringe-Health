package com.example.technopa.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technopa.models.Repository
import com.example.technopa.models.MainUser

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