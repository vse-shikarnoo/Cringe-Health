package com.example.technopa


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.technopa.diet.views.DetailDietFragment
import com.example.technopa.diet.views.DietListFragment
import com.example.technopa.interfaces.FragmentInterface
import com.example.technopa.profile.Views.ProfileFragment
import com.example.technopa.trainings.Views.DetailTrainingFragment
import com.example.technopa.trainings.Views.TrainingListFragment
import com.example.technopa.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), FragmentInterface {

    private var binding: ActivityMainBinding? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)



        var b = true

        val view = binding?.root
        setContentView(view)
        if(supportFragmentManager.findFragmentById(R.id.mainContainer)==null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, MainFragment())
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
           .replace(R.id.mainContainer, fragment)
           .addToBackStack(fragment.toString())
           .commit()
    }

    override fun onItemSelectedDiets(dieta: Dieta) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, DetailDietFragment.newInstance(dieta))
            .addToBackStack(dieta.toString())
            .commit()
    }

    override fun onItemSelectedTrainings(training: Training) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, DetailTrainingFragment.newInstance(training))
            .addToBackStack(training.toString())
            .commit()
    }


}