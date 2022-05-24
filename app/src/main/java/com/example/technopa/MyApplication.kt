package com.example.technopa

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.hardware.SensorManager

class MyApplication : Application() {
    var mSettings: SharedPreferences? = null
    private val APP_PREFERENCES = R.string.APP_PREFERENCES.toString()
    var sensorManager: SensorManager? = null


    override fun onCreate() {
        super.onCreate()
        mSettings = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager?
    }
}