package com.example.technopa


import android.app.AlertDialog
import android.content.Context.MODE_PRIVATE
import android.content.Context.SENSOR_SERVICE
import android.content.SharedPreferences
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.technopa.databinding.MainFragmentLayoutBinding

import java.util.*


//Главный экран
//Переходы на экраны персональной диеты/тренировки и профиля
//
class MainFragment : Fragment(R.layout.main_fragment_layout), SensorEventListener, StepListener {

    private var binding: MainFragmentLayoutBinding? = null
    private var simpleStepDetector: StepDetector? = null
    private var sensorManager: SensorManager? = null

    private var numSteps: Int = 0
    private var dnm: Int = 10000

    private val TEXT_NUM_STEPS = "Шаги: "
    private val APP_PREFERENCES = R.string.APP_PREFERENCES.toString()
    private val APP_PREFERENCES_STEPS = R.string.APP_PREFERENCES_STEPS.toString()
    private val APP_PREFERENCES_DNM_STEPS = "dnm"
    private val APP_PREFERENCES_DATE = "date"


    private var mSettings: SharedPreferences? = null


    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(
            this,
            sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_FASTEST
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding = MainFragmentLayoutBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)



        mSettings = activity?.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)


        val date = Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString()
        Log.d("Date", date)


        if (mSettings!!.contains(APP_PREFERENCES_DATE)) {
            if (date != mSettings!!.getString(APP_PREFERENCES_DATE, "")) {
                saveSteps(0)
                saveDate(date)
            }
        } else {
            saveDate(date)
            saveSteps(0)
        }
        numSteps = mSettings!!.getInt(APP_PREFERENCES_STEPS, 0)
        dnm = mSettings!!.getInt(APP_PREFERENCES_DNM_STEPS, 0)

        if (mSettings!!.contains(APP_PREFERENCES_STEPS)) {
            binding?.textViewXD?.text = numSteps.toString()
        }


        updateProgressBar()

        binding?.textViewXD?.setOnClickListener {
            numSteps += 1
            saveSteps(numSteps)


            binding?.shagiProgressBar?.incrementProgressBy(1)
            binding?.textViewXD?.text = TEXT_NUM_STEPS.plus(numSteps)
        }

        binding?.shagiProgressBar?.setOnClickListener {
            choseDNM()

        }

        sensorManager =
            requireContext().applicationContext.getSystemService(SENSOR_SERVICE) as SensorManager
        //sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        simpleStepDetector = StepDetector()
        simpleStepDetector!!.registerListener(this)

    }

    private fun updateProgressBar() {
        binding?.textViewXD?.text = TEXT_NUM_STEPS.plus(numSteps)
        binding?.shagiProgressBar?.max = dnm
        binding?.shagiProgressBar?.progress = numSteps
    }

    private fun choseDNM() {
        val listDNM =
            arrayOf("0", "100", "10000", "12000", "14000", "16000", "18000", "20000")
        AlertDialog.Builder(requireContext())
            .setTitle("Выберите дневную норму шагов")
            .setItems(listDNM) { _, position ->
                if (position == 0) {
                    numSteps = 0
                    saveSteps(numSteps)
                    toast("Шаги сброшены")
                } else {
                    dnm = listDNM[position].toInt()
                    mSettings?.edit()
                        ?.putInt(APP_PREFERENCES_DNM_STEPS, dnm)
                        ?.apply()
                    toast("Дневная норма шагов = $dnm")
                }

                updateProgressBar()
            }
            .show()


    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector!!.updateAccelerometer(
                event.timestamp,
                event.values[0],
                event.values[1],
                event.values[2]
            )
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

    override fun step(timeNs: Long) {
        numSteps++
        binding?.shagiProgressBar?.incrementProgressBy(1)
        binding?.textViewXD?.text = TEXT_NUM_STEPS.plus(numSteps)

        saveSteps(numSteps)
    }

    fun saveSteps(steps: Int) {
        mSettings?.edit()?.putInt(APP_PREFERENCES_STEPS, steps)?.apply()
    }

    fun saveDate(date: String) {
        mSettings?.edit()?.putString(APP_PREFERENCES_DATE, date)?.apply()
    }


}