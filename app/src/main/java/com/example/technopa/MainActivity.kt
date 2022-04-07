package com.example.technopa

import com.example.technopa.Fragments.ProfileFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.technopa.Fragments.MainFragment
import com.example.technopa.Fragments.PersonalTrainingFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val mainFragment = MainFragment()
    private val personalTrainingFragment = PersonalTrainingFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BottomView.selectedItemId = R.id.mainFragment

        BottomView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.profileFragment -> replaceFragment(profileFragment)
                R.id.mainFragment -> replaceFragment(mainFragment)
                R.id.personalTrainingFragment -> replaceFragment(personalTrainingFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        if (fragment!=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView3, fragment)
            transaction.commit()
        }
    }
}