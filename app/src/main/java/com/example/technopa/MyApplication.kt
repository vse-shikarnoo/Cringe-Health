package com.example.technopa

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.hardware.SensorManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MyApplication : Application() {


    var mSettings: SharedPreferences? = null
    private val APP_PREFERENCES = R.string.APP_PREFERENCES.toString()
    var sensorManager: SensorManager? = null

    private val flagLiveData = MutableLiveData<Boolean>()




    override fun onCreate(){
        super.onCreate()
        mSettings = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager?

    }


}