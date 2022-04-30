package com.example.technopa.Diet.Repos


import com.example.technopa.Dieta
import com.example.technopa.FirebaseNetwork
import com.example.technopa.User

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