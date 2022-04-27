package com.example.technopa



import android.content.Context
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
import androidx.compose.ui.layout.TestModifierUpdaterLayout
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.technopa.databinding.MainFragmentLayoutBinding


//Главный экран
//Переходы на экраны персональной диеты/тренировки и профиля
//
class MainFragment : Fragment(R.layout.main_fragment_layout), SensorEventListener, StepListener {

    private var binding: MainFragmentLayoutBinding? = null

    var simpleStepDetector: StepDetector? = null
    var sensorManager: SensorManager? = null;
    private val TEXT_NUM_STEPS = "Number of Steps: "
    private var numSteps: Int = 0
    var sPref :SharedPreferences? = null


    override fun onResume() {
        super.onResume()
        numSteps = 0
        sensorManager!!.registerListener(this,sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_FASTEST)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        sPref = activity?.getPreferences(MODE_PRIVATE)
        numSteps = sPref?.getInt("STEPS",numSteps)?.toInt()?:0
        binding?.textViewXD?.text = TEXT_NUM_STEPS.plus(numSteps)


        binding = MainFragmentLayoutBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        binding!!.textViewXD.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToPersonalDietFragment())
        }

        binding!!.textViewXD.setOnLongClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToPersonalTrainingFragment())
            true
        }

        binding!!.textviewDietTrain.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToDietListFragment())

        }

        binding!!.textviewDietTrain.setOnLongClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToTrainingListFragment())
            true
        }

        binding!!.textviewProfile.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToProfileFragment())
        }

        sensorManager = requireContext().applicationContext.getSystemService(SENSOR_SERVICE) as SensorManager
        //sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        simpleStepDetector = StepDetector()
        simpleStepDetector!!.registerListener(this)

    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()

    }



}