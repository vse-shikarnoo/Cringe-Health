package com.example.technopa.trainings.Repos

import com.example.technopa.Classes.Training
import com.example.technopa.FirebaseNetwork


class TrainingListRepo {

    fun getTrainings(
        callback: (List<Training>) -> Unit,
        errorCallBack: (e: Throwable) -> Unit
    ) {
        FirebaseNetwork().getTrainings({ trainingList ->
            callback(trainingList)
        }, { error ->
            errorCallBack(error)
        })
    }
}
