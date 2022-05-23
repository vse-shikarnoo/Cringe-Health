package com.example.technopa.profile.Models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technopa.profile.Repos.MainUser
import com.example.technopa.profile.Repos.Repository

class ProfileVM : ViewModel() {

    val user = MutableLiveData<MainUser?>()
    var progressText = MutableLiveData<String>()
    var weightNp2 = MutableLiveData<Int>()
    private var repository = Repository()

    var desiredWeightNp2 = MutableLiveData<Int>()

    init {
        user.value = repository.getUser()
        progressText.value = (
            (
                (user.value?.weight?.let { user.value?.desired_weight?.div(it) })?.times(
                    100
                )
                )?.toInt()
            ).toString() + "%"
        weightNp2.value = ((user.value!!.weight - user.value!!.weight.toInt()) * 10).toInt()
    }

    fun setWeight(IntPart: Int, FracPart: Int) {
        val user1 = user.value
        user1?.weight = IntPart.toDouble() + (FracPart.toDouble() / 10)
        user.value = user1
        repository.sendUser(user1)
    }

    fun setHeight(newHeight: Int) {
        val user1 = user.value
        user1?.height = newHeight
        repository.sendUser(user1)
    }
    fun setNameSurname(newName: String, newSurname: String) {
        val user1 = user.value
        user1?.name = newName
        user1?.surname = newSurname
        repository.sendUser(user1)
    }
    fun setDesWeight(IntPart: Int, FractionalPart: Int) {
        val user1 = user.value
        user1?.desired_weight = IntPart.toDouble() + (FractionalPart.toDouble() / 10)
        repository.sendUser(user1)
    }
}
