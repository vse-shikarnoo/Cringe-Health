package com.example.technopa.MainInfo.Model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technopa.MainInfo.Repo.MainRepo

class MainModel(application: Application) : AndroidViewModel(application) {
    private var repository = MainRepo(application)

    private val stepsLiveData = MutableLiveData<Int>()
    private val dnsLiveData = MutableLiveData<Int>()

    val steps: LiveData<Int>
        get() = stepsLiveData

    val dns: LiveData<Int>
        get() = dnsLiveData

    init {
        getSteps()
        getDNS()
        checkDate()

    }


    fun incrementStep(){
        stepsLiveData.postValue(stepsLiveData.value?.plus(1))
        saveSteps(steps.value?:0)
    }

    fun saveSteps(steps: Int) {
        repository.saveSteps(steps)
    }

    fun saveDNS(dns: Int){
        repository.saveDNS(dns)
    }

    private fun getSteps(){
        repository.getSteps { steps ->
            stepsLiveData.postValue(steps)
        }
    }

    private fun getDNS(){
        repository.getDNS { dns ->
            dnsLiveData.postValue(dns)
        }
    }

    private fun checkDate() {
        repository.checkDate {
            if (!it){
                stepsLiveData.postValue(0)
            }
        }

    }
}