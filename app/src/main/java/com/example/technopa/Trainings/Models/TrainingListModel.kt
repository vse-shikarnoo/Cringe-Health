package com.example.technopa.Trainings.Models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technopa.Training
import com.example.technopa.Trainings.Repos.TrainingListRepo

class TrainingListModel: ViewModel() {

    private var repository = TrainingListRepo()

    private val trainingListLiveData = MutableLiveData<List<Training>>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()
    private val isErrorLiveData = MutableLiveData<Throwable?>()

    val trainingList: LiveData<List<Training>>
        get() = trainingListLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    val isError: LiveData<Throwable?>
        get() = isErrorLiveData

    fun getTrainings(){
        isErrorLiveData.postValue(null)
        isLoadingLiveData.postValue(true)
        repository.getTrainings({trainingList ->
            isLoadingLiveData.postValue(false)
            trainingListLiveData.postValue(trainingList)
        },{error ->
            isErrorLiveData.postValue(error)
            isLoadingLiveData.postValue(false)
        })
    }
}