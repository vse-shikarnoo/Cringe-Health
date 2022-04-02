package com.example.technopa

import com.google.firebase.database.FirebaseDatabase



data class User(
    val id:Long,
    val nickname:String,
    val height:Double,
    val weight:Double,
    //val statistic:Statistic,
    //val Achievements:List<Achievement>
    )

data class Statistic(
    val id:Long,
    val TrainingList:Map<String,List<Training>>, //Здесь String это дата
    val PriemPishiList:Map<String,List<PriemPishi>>,)

data class Achievement(
    //val picture:Picture,
    val data:String,
    val opisanie:String)



data class Training(
    val exercises:List<Exercise>,
    val kalorii:Double,
    val time:Int)

data class Exercise(
    val title:String,
    val povtoreniya:Pair<Int,Int>,
    val opisanie:String)

data class PriemPishi(
    val title:String,
    val eda:List<Eda>,
    val kaloriiO:Double, //Каллории и БЖУ общее за прием пищи
    val belkiO:Double,
    val zhiriO:Double,
    val uglevodiO:Double)

data class Eda(
    val title:String,
    val kalorii:Double,
    val belki:Double,
    val zhiri:Double,
    val uglevodi:Double)

data class Dieta(
    val title:String,
    val priemPishiList:List<PriemPishi>,
    val kaloriipd:Double, // Каллории и БЖУ в день
    val belkipd:Double,
    val zhiripd:Double,
    val uglevodipd:Double,
    val opisanie:String)



class FirebaseNetwork {
    private val database = FirebaseDatabase.getInstance()
    val refUsers = database.getReference("Users")
    private val refTrainings = database.getReference("Trainings")
    private val refDiets = database.getReference("Diets")

    fun setUser(user: User){
        refUsers.setValue(user)
    }

}
