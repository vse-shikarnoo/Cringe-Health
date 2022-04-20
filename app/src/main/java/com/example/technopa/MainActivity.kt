package com.example.technopa

import com.example.technopa.Fragments.ProfileFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.example.technopa.Diet.Views.DietListFragment


import com.example.technopa.Trainings.Views.TrainingListFragment
import com.example.technopa.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val mainFragment = MainFragment()
    private val personalTrainingFragment = PersonalTrainingFragment()
    private val profileFragment = ProfileFragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var fl:Boolean = true

        setContentView(R.layout.activity_main)
        BottomView.selectedItemId = R.id.mainFragment

        BottomView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.profileFragment -> replaceFragment(profileFragment)
                R.id.mainFragment -> replaceFragment(mainFragment)
                R.id.personalTrainingFragment -> {
                    if (!fl){
                        replaceFragment(DietListFragment())
                        fl = true
                    }
                    else{
                        replaceFragment(TrainingListFragment())
                        fl = false
                    }
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.commit()

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}