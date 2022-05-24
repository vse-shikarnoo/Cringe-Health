package com.example.technopa

import android.app.*
import android.content.Intent
import android.content.SharedPreferences
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

class ForegroundService() : Service(), SensorEventListener,
    StepListener {

    val CHANNEL_ID: String = "ForegroundServiceChannel"
    private val APP_PREFERENCES = R.string.APP_PREFERENCES.toString()
    private val APP_PREFERENCES_STEPS = R.string.APP_PREFERENCES_STEPS.toString()
    private val APP_PREFERENCES_DNS_STEPS = "dayNormaSteps"
    private val APP_PREFERENCES_DATE = "date"
    private val APP_PREFERENCES_AVHIEVMENTS = "achievments"


    private var mSettings: SharedPreferences? = null

    private val date = Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString()


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

        mSettings = MyApplication().mSettings

        sensorManager = MyApplication().sensorManager
        // sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        simpleStepDetector = StepDetector()
        simpleStepDetector?.registerListener(this)

        sensorManager?.registerListener(
            this,
            sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_FASTEST
        )


    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val input: String? = intent?.getStringExtra("inputExtra")
        createNotificationChannel()
        val notificationIntent: Intent = Intent(this, MainActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, 0
        )

        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Считаем шаги")
            .setContentText(input)
            .setSmallIcon(R.drawable.step)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)

        //do step


        return START_NOT_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel: NotificationChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager: NotificationManager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
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
        mSettings?.edit()?.putInt(APP_PREFERENCES_STEPS, steps)?.apply()

    }

    private fun getSteps() {

        stepsLiveData.postValue(mSettings?.getInt(APP_PREFERENCES_STEPS, 0))
        checkDate()

    }

    private fun getDNS() {

        dnsLiveData.postValue(mSettings?.getInt(APP_PREFERENCES_DNS_STEPS, 0))

    }

    private fun checkDate() {

        val flag = if (mSettings?.contains(APP_PREFERENCES_DATE) == true) {
            if (date != mSettings?.getString(APP_PREFERENCES_DATE, "")) {
                saveSteps(0)
                mSettings?.edit()?.putString(APP_PREFERENCES_DATE, date)?.apply()
                false
            } else {
                true
            }
        } else {
            mSettings?.edit()?.putString(APP_PREFERENCES_DATE, date)?.apply()
            saveSteps(0)
            false
        }

        if (!flag) {
            stepsLiveData.postValue(0)

        }
    }

}

