package com.example.technopa.models

class Repository {

    @JvmName("getUser1")
    fun getUser(): User {
        return User()
    }

    fun sendUser(user1: User) {
    }

}