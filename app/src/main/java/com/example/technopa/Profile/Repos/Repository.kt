package com.example.technopa.Profile.Repos

class Repository {


    fun getUser(): MainUser {
        return Singleton.currentUser
    }

    fun sendUser(user1: MainUser?) {
        if (user1 != null) Singleton.currentUser = user1
    }

}