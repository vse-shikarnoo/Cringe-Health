package com.example.technopa.main.view

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.technopa.ForegroundService
import com.example.technopa.R
import com.example.technopa.databinding.MainFragmentLayoutBinding
import com.example.technopa.main.model.MainModel
import com.example.technopa.toast
import kotlin.properties.Delegates


// Главный экран
// Переходы на экраны персональной диеты/тренировки и профиля
//

//
class MainFragment : Fragment(R.layout.main_fragment_layout) {

    private var viewModel: MainModel? = null

    private var binding: MainFragmentLayoutBinding? = null


    private val TEXT_NUM_STEPS = "Шаги: "
    private val TEXT_DNS = "Дневная норма шагов : "
    private var numSteps by Delegates.notNull<Int>()
    private var dayNormalSteps by Delegates.notNull<Int>()




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = MainFragmentLayoutBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainModel::class.java)

        observe()


        binding?.textViewStep?.setOnClickListener {
            viewModel?.incrementStep()
            updateState()

            Log.d("Test", "Imitation step numSteps = ${viewModel?.steps?.value}")
        }

        binding?.shagiProgressBar?.setOnClickListener {
            choseDNM()
        }


    }

    private fun choseDNM() {
        val listDNM =
            arrayOf("0", "100", "10000", "12000", "14000", "16000", "18000", "20000")
        AlertDialog.Builder(requireContext())
            .setTitle("Выберите дневную норму шагов")
            .setItems(listDNM) { _, position ->
                if (position == 0) {
                    viewModel?.saveSteps(0)

                } else {
                    viewModel?.saveDNS(listDNM[position].toInt())

                }

            }
            .show()
    }

    override fun onResume() {
        super.onResume()
        val serviceIntent: Intent = Intent(requireContext(), ForegroundService::class.java)

        activity?.stopService(serviceIntent)
        Log.d("MainFragment","Resumed;  Foreground Service stopped")
    }


    override fun onPause() {
        super.onPause()
        val serviceIntent: Intent = Intent(requireContext(), ForegroundService::class.java)
        serviceIntent.putExtra("inputExtra",numSteps)


        ContextCompat.startForegroundService(requireContext(), serviceIntent)
        Log.d("MainFragment","Paused; Foreground Service started")
    }




    private fun observe() {
        viewModel?.dns?.observe(viewLifecycleOwner) {
            updateState()

        }
        viewModel?.steps?.observe(viewLifecycleOwner) {
            updateState()
            if (it==0){
                toast("Шаги сброшены")
            }
        }
    }

    private fun updateState() {
        numSteps = viewModel?.steps?.value ?: 0
        dayNormalSteps = viewModel?.dns?.value ?: 0
        if (numSteps == dayNormalSteps){
            toast("Вы уже выполнили дневную норму шагов")
        }
        Log.d("Test DNS", dayNormalSteps.toString())
        binding?.shagiProgressBar?.max = dayNormalSteps
        binding?.shagiProgressBar?.progress = numSteps
        binding?.textViewStep?.text = TEXT_NUM_STEPS.plus(numSteps)
        binding?.textViewDNS?.text = TEXT_DNS.plus(dayNormalSteps)

        Log.d("Test", "After update steps = $numSteps; dns = $dayNormalSteps")
    }
}
