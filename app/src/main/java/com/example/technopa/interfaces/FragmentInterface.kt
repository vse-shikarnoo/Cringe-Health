package com.example.technopa.interfaces

import androidx.fragment.app.Fragment
import com.example.technopa.Dieta
import com.example.technopa.Training

interface FragmentInterface {
    fun openFragment(fragment: Fragment)

    fun onItemSelectedDiets(dieta: Dieta)
    fun onItemSelectedTrainings(training: Training)
}
