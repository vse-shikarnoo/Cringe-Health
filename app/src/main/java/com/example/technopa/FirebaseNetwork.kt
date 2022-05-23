package com.example.technopa

import android.os.Parcelable
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.parcel.Parcelize
import java.lang.Exception

@Parcelize
data class User(
    val id: String = "",
    val name: String? = "",
    val surname: String? = "",
    val height: Double? = 0.0,
    val weight: Double? = 0.0,
    // val statistic:Statistic,
    // val Achievements:List<Achievement>
) : Parcelable

data class Statistic(
    val id: Long,
    val TrainingList: Map<String, List<Training>>, // Здесь String это дата
    val PriemPishiList: Map<String, List<PriemPishi>>,
)

data class Achievement(
    // val picture:Picture,
    val data: String,
    val opisanie: String
)

@Parcelize
data class Training(
    var id: Long? = null,
    val title: String? = ",",
    val exercises: List<Exercise> = emptyList(),
    val kalorii: Double = 0.0,
    val time: Int = 0,
    val opisanie: String? = ""
) : Parcelable

@Parcelize
data class Exercise(
    val title: String = "",
    val povtoreniya: String? = null,
    val opisanie: String = ""
) : Parcelable

@Parcelize
data class PriemPishi(
    val title: String? = null,
    val eda: List<Eda>? = null,
    var kaloriiO: Double? = 0.0, // Каллории и БЖУ общее за прием пищи
    var belkiO: Double? = 0.0,
    var zhiriO: Double? = 0.0,
    var uglevodiO: Double? = 0.0
) : Parcelable

@Parcelize
data class Eda(
    val title: String? = null,
    val kalorii: Double? = null,
    val belki: Double? = null,
    val zhiri: Double? = null,
    val uglevodi: Double? = null
) : Parcelable

@Parcelize
data class Dieta(
    var title: String? = "",
    var id: Long? = null,
    var priemPishiList: List<PriemPishi>? = emptyList(),
    var kaloriipd: Double? = 0.0, // Каллории и БЖУ в день
    var opisanie: String? = ""
) : Parcelable {
    override fun toString(): String {
        return "Dieta[title = $title;priem pishi = $priemPishiList]"
    }
}

class FirebaseNetwork {
    private val database = FirebaseDatabase.getInstance()
    private val refUsers = database.getReference("Users")
    private val refTrainings = database.getReference("Trainings")
    private val refDiets = database.getReference("Diets")

    fun setUser(user: User) {
        refUsers.child(user.id).setValue(user)
    }

    fun getUser(
        call: (User) -> Unit,
        errorCall: (error: Throwable) -> Unit,
        userId: String
    ) {
        refUsers.child(userId).get().addOnSuccessListener {
            Log.i("getUser", "Got value ${it.getValue(User::class.java)}")
        }.addOnFailureListener {
            Log.e("getUser", "Error getting data", it)
        }
    }

    fun getDiets(
        call: (MutableList<Dieta>) -> Unit,
        errorCall: (error: Throwable) -> Unit
    ) {
        val listDiets = mutableListOf<Dieta>()
        refDiets.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (postSnapshot in snapshot.children) {
                    listDiets.add(postSnapshot.getValue(Dieta::class.java) ?: Dieta())
                }
                call((listDiets+listDiets+listDiets) as MutableList<Dieta>)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Test", error.message)
                errorCall(Exception(error.message))
            }
        })
    }

    fun getTrainings(
        call: (MutableList<Training>) -> Unit,
        errorCall: (error: Throwable) -> Unit
    ) {
        val listTrainings = mutableListOf<Training>()
        refTrainings.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (postSnapshot in snapshot.children) {
                    listTrainings.add(postSnapshot.getValue(Training::class.java) ?: Training())
                }
                Log.d("Test", "Success")
                call((listTrainings+listTrainings+listTrainings) as MutableList<Training>)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Test", error.message)
                errorCall(error.toException())
            }
        })
    }
}
