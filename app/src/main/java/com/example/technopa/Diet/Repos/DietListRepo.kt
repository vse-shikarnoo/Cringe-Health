package com.example.technopa.Diet.Repos


import com.example.technopa.Dieta
import com.example.technopa.FirebaseNetwork

class DietListRepo {

    fun getDiets(
        callback: (List<Dieta>) -> Unit,
        errorCallBack: (e:Throwable) -> Unit
    ){
        FirebaseNetwork().getDiets({dietList ->
            callback(dietList)
        },{error ->
            errorCallBack(error)
        })
    }
}