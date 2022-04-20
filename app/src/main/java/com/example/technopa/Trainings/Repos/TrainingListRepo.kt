package com.example.technopa.Trainings.Repos

import com.example.technopa.FirebaseNetwork
import com.example.technopa.Training

class TrainingListRepo {

    fun getTrainings(
        callback: (List<Training>) -> Unit,
        errorCallBack: (e:Throwable) -> Unit
    ){
        FirebaseNetwork().getTrainings({ trainingList ->
            callback(trainingList)
        },{error ->
            errorCallBack(error)
        })
    }
}