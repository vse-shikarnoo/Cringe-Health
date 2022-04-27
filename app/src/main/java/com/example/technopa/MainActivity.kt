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
    private val trainingFragment = TrainingListFragment()
    private val profileFragment = ProfileFragment()
    private  val dietFragment = DietListFragment()
    private var binding: ActivityMainBinding? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        var b: Boolean = true
        var fl:Boolean = true
        val view = binding?.root
        setContentView(view)
        binding?.BottomView?.selectedItemId = R.id.mainFragment

        setContentView(R.layout.activity_main)
        BottomView.selectedItemId = R.id.mainFragment

        BottomView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.profileFragment -> replaceFragment(profileFragment)
                R.id.mainFragment -> replaceFragment(mainFragment)
                R.id.trainingListFragment -> {
                    if (b) {
                        replaceFragment(trainingFragment)
                        b = false
                    } else {
                        replaceFragment(dietFragment)
                        b = true
                    }
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView3, fragment)
            transaction.commit()

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}