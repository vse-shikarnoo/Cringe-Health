package com.example.technopa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.technopa.databinding.MainFragmentLayoutBinding


//Главный экран
//Переходы на экраны персональной диеты/тренировки и профиля
//
class MainFragment : Fragment(R.layout.main_fragment_layout) {

    private var binding: MainFragmentLayoutBinding? = null




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()

    }
}