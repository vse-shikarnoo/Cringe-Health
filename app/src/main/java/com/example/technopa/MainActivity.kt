package com.example.technopa

import com.example.technopa.Fragments.ProfileFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import com.example.technopa.Diet.Views.DetailDietFragment
import com.example.technopa.Diet.Views.DietListFragment
import com.example.technopa.Interfaces.FragmentInterface
import com.example.technopa.Trainings.Views.DetailTrainingFragment


import com.example.technopa.Trainings.Views.TrainingListFragment
import com.example.technopa.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity(), FragmentInterface {

    private var binding: ActivityMainBinding? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)



        var b: Boolean = true

        val view = binding?.root
        setContentView(view)
        if(supportFragmentManager.findFragmentById(R.id.fragmentContainerView)==null) {
            Log.d("CheckContainer",(supportFragmentManager.findFragmentById(R.id.fragmentContainerView)==null).toString())
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, MainFragment())
                .commit()
        }
        binding?.BottomView?.selectedItemId = R.id.mainFragment




        binding?.BottomView?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.profileFragment -> openFragment(ProfileFragment())
                R.id.mainFragment -> openFragment(MainFragment())
                R.id.trainingListFragment -> {
                    b = if (b) {

                        openFragment(TrainingListFragment())
                        false
                    } else {
                        openFragment(DietListFragment())
                        true
                    }
                }
            }
            true
        }
    }



    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount

        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    override fun openFragment(fragment: Fragment) {
       supportFragmentManager.beginTransaction()
           .replace(R.id.fragmentContainerView, fragment)
           .addToBackStack(fragment.toString())
           .commit()
    }

    override fun onItemSelectedDiets(dieta: Dieta) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, DetailDietFragment.newInstance(dieta))
            .addToBackStack(dieta.toString())
            .commit()
    }

    override fun onItemSelectedTrainings(training: Training) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, DetailTrainingFragment.newInstance(training))
            .addToBackStack(training.toString())
            .commit()
    }


}