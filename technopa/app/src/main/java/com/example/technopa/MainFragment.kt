package com.example.technopa

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.main_fragment_layout.*

class MainFragment : Fragment(R.layout.main_fragment_layout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewXD.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToPersonalTrainingFragment())
        }
        textViewXD.setOnLongClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToPersonalDietFragment())
            true
        }
    }
}