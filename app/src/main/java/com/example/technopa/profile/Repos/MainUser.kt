package com.example.technopa.profile.Repos

class MainUser {
    var name = "Name"
    var surname = "Surname"
    var height = 180
    var weight = 75.0
    var desired_weight = 70.0
}

object Singleton{

    var currentUser = MainUser()

}