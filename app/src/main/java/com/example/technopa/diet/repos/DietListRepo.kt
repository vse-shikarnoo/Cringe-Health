package com.example.technopa.diet.repos

import com.example.technopa.Classes.Dieta

import com.example.technopa.FirebaseNetwork

class DietListRepo {

    fun getDiets(
        callback: (List<Dieta>) -> Unit,
        errorCallBack: (e: Throwable) -> Unit
    ) {
        FirebaseNetwork().getDiets({ dietList ->
            callback(dietList)
        }, { error ->
            errorCallBack(error)
        })
    }
}
