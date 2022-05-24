package com.example.technopa.main.model

import android.app.Application
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.technopa.main.repo.MainRepo
import com.example.technopa.StepDetector
import com.example.technopa.StepListener

class MainModel(application: Application) : AndroidViewModel(application), SensorEventListener,
    StepListener {
    private var repository = MainRepo(application)
    private var simpleStepDetector: StepDetector? = null
    private var sensorManager: SensorManager? = null

    private val stepsLiveData = MutableLiveData<Int>()
    private val dnsLiveData = MutableLiveData<Int>()

    val steps: LiveData<Int>
        get() = stepsLiveData

    val dns: LiveData<Int>
        get() = dnsLiveData

    init {
        getSteps()
        getDNS()



        sensorManager =
            application.applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        // sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        simpleStepDetector = StepDetector()
        simpleStepDetector?.registerListener(this)

        sensorManager?.registerListener(
            this,
            sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_FASTEST
        )


    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector?.updateAccelerometer(
                event.timestamp,
                event.values[0],
                event.values[1],
                event.values[2]
            )
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

    override fun step(timeNs: Long) {
        incrementStep()
    }

    fun incrementStep() {
        stepsLiveData.postValue(stepsLiveData.value?.plus(1))
        saveSteps(steps.value?.plus(1) ?: 0)
    }

    fun saveSteps(
        steps: Int
    ) {
        stepsLiveData.postValue(steps)
        repository.saveSteps(steps)

    }

    fun saveDNS(
        dns: Int
    ) {
        dnsLiveData.postValue(dns)
        repository.saveDNS(dns)

    }

    private fun getSteps() {
        repository.getSteps { steps ->
            stepsLiveData.postValue(steps)
            checkDate()
        }
    }

    private fun getDNS() {
        repository.getDNS { dns ->
            dnsLiveData.postValue(dns)
        }
    }

    private fun checkDate() {
        repository.checkDate {
            if (!it) {
                stepsLiveData.postValue(0)
            }
        }
    }
}
