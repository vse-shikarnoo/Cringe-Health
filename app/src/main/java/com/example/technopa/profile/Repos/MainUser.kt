package com.example.technopa.profile.Repos

import com.example.technopa.Profile.Repos.item

class MainUser {

    var name = "Vyacheslav"
    var surname = "Gorlov"
    var height = 180
    var weight = 95.0
    var desired_weight = 85.0
    var eating : MutableList<item> = mutableListOf(item("Завтрак", 150), item("Полдник", 140))

}

object Singleton{

    var currentUser = MainUser()

}
