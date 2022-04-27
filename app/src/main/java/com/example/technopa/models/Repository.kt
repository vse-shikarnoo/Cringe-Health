package com.example.technopa.models

class Repository {


    fun getUser(): User {
        return Singleton.currentUser
    }

    fun sendUser(user1: User?) {
        if (user1 != null) Singleton.currentUser = user1
    }

}