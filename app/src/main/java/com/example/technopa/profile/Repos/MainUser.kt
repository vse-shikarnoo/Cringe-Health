package com.example.technopa.profile.Repos

class MainUser {

    var name = "Vyacheslav"
    var surname = "Gorlov"
    var height = 180
    var weight = 95.0
    var desired_weight = 85.0
    //val statistic: Statistic,
    //val achievment: List<Achievment>

}

object Singleton{

    var currentUser = MainUser()

}
