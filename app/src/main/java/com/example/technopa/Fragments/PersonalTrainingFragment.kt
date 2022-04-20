package com.example.technopa.Fragments

<<<<<<< HEAD
import androidx.fragment.app.Fragment
import com.example.technopa.R

//Экран персональной тренировки
class PersonalTrainingFragment : Fragment(R.layout.personal_training_layout) {
=======
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.technopa.R

class PersonalTrainingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.personal_training_layout, container, false)
    }

>>>>>>> origin/vygorlov
}