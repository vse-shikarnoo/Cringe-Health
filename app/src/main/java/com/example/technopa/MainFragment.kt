package com.example.technopa



import android.content.Context.MODE_PRIVATE
import android.content.Context.SENSOR_SERVICE
import android.content.SharedPreferences
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.technopa.databinding.MainFragmentLayoutBinding


//Главный экран
//Переходы на экраны персональной диеты/тренировки и профиля
//
class MainFragment : Fragment(R.layout.main_fragment_layout), SensorEventListener, StepListener {

    private var binding: MainFragmentLayoutBinding? = null
    private var simpleStepDetector: StepDetector? = null
    private var sensorManager: SensorManager? = null

    private var numSteps: Int = 0
    private val dnm: Int = 100

    private val TEXT_NUM_STEPS = "Шаги: "
    private val APP_PREFERENCES = R.string.APP_PREFERENCES.toString()
    private val APP_PREFERENCES_STEPS = R.string.APP_PREFERENCES_STEPS.toString()


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
        numSteps = mSettings!!.getInt(APP_PREFERENCES_STEPS, 0)
        if (mSettings!!.contains(APP_PREFERENCES_STEPS)) {
            binding?.textViewXD?.text = numSteps.toString()
        }

        binding?.textViewXD?.text = TEXT_NUM_STEPS.plus(numSteps)
        binding?.shagiProgressBar?.max = 10
        binding?.textViewXD?.setOnClickListener {
            numSteps--
            mSettings?.edit()?.putInt(APP_PREFERENCES_STEPS, numSteps)?.apply()

            binding?.shagiProgressBar?.progress = (numSteps / dnm)
            binding?.textViewXD?.text = TEXT_NUM_STEPS.plus(numSteps)
        }

        sensorManager =
            requireContext().applicationContext.getSystemService(SENSOR_SERVICE) as SensorManager
        //sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        simpleStepDetector = StepDetector()
        simpleStepDetector!!.registerListener(this)

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

        mSettings?.edit()?.putInt(APP_PREFERENCES_STEPS, numSteps)?.apply()
    }


}