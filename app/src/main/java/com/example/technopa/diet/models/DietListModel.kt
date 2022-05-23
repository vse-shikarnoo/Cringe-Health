package com.example.technopa.diet.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technopa.Classes.Dieta

import com.example.technopa.diet.repos.DietListRepo

class DietListModel : ViewModel() {

    private var repository = DietListRepo()

    private val dietListLiveData = MutableLiveData<List<Dieta>>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()
    private val isErrorLiveData = MutableLiveData<Throwable?>()

    val dietList: LiveData<List<Dieta>>
        get() = dietListLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    val isError: LiveData<Throwable?>
        get() = isErrorLiveData

    fun getDiets() {
        isErrorLiveData.postValue(null)
        isLoadingLiveData.postValue(true)
        repository.getDiets(
            { dietList ->
                isLoadingLiveData.postValue(false)
                dietListLiveData.postValue(dietList)
            }, { error ->
            isErrorLiveData.postValue(error)
            isLoadingLiveData.postValue(false)
        }
        )
    }
}
