package com.example.technopa

import android.os.Parcelable
import android.util.Log
import com.example.technopa.Classes.Dieta
import com.example.technopa.Classes.PriemPishi
import com.example.technopa.Classes.Training
import com.example.technopa.Classes.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.parcel.Parcelize
import java.lang.Exception









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
