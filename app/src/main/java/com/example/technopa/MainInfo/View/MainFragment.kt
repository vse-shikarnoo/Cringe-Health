package com.example.technopa.MainInfo.View


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
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.technopa.MainInfo.Model.MainModel
import com.example.technopa.R
import com.example.technopa.StepDetector
import com.example.technopa.StepListener
import com.example.technopa.databinding.MainFragmentLayoutBinding
import com.example.technopa.toast

import java.util.*
import kotlin.properties.Delegates


//Главный экран
//Переходы на экраны персональной диеты/тренировки и профиля
//

//
class MainFragment : Fragment(R.layout.main_fragment_layout), SensorEventListener, StepListener {

    private var viewModel: MainModel? = null

    private var binding: MainFragmentLayoutBinding? = null
    private var simpleStepDetector: StepDetector? = null
    private var sensorManager: SensorManager? = null



    private val TEXT_NUM_STEPS = "Шаги: "
    private var numSteps by Delegates.notNull<Int>()
    private var dayNormalSteps by Delegates.notNull<Int>()








    override fun onResume() {


        //Разобраться с изменением шагов
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


        viewModel = MainModel(requireActivity().application)


        updateProgressBar()
        observe()

        binding?.textViewXD?.setOnClickListener {
            viewModel?.incrementStep()
            viewModel?.saveSteps(numSteps)
            updateProgressBar()
            Log.d("Test", "Имитация шага numSteps = ${viewModel?.steps?.value}")
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
        updateState()
        binding?.shagiProgressBar?.max = dayNormalSteps
        Log.d("Model param", "steps = $numSteps; dns = $dayNormalSteps")


    }

    private fun choseDNM() {
        val listDNM =
            arrayOf("0", "100", "10000", "12000", "14000", "16000", "18000", "20000")
        AlertDialog.Builder(requireContext())
            .setTitle("Выберите дневную норму шагов")
            .setItems(listDNM) { _, position ->
                if (position == 0) {
                    numSteps = 0
                    viewModel?.saveSteps(numSteps)
                    toast("Шаги сброшены")
                } else {
                    dayNormalSteps = listDNM[position].toInt()
                    viewModel?.saveDNS(dayNormalSteps)
                    toast("Дневная норма шагов = $dayNormalSteps")
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
        viewModel?.saveSteps(numSteps)
        Log.d("Test", "Сделвн шаг numSteps = $numSteps")
    }

    private fun observe(){
        viewModel?.dns?.observe(viewLifecycleOwner){
            dayNormalSteps = viewModel?.dns?.value?:0
            updateState()
        }
        viewModel?.steps?.observe(viewLifecycleOwner){
            numSteps = viewModel?.steps?.value?:0
            updateState()
        }
    }

    private fun updateState(){
        numSteps = viewModel?.steps?.value?:0
        dayNormalSteps = viewModel?.dns?.value?:0
        binding?.shagiProgressBar?.progress = numSteps
        binding?.textViewXD?.text = TEXT_NUM_STEPS.plus(numSteps)

    }




}