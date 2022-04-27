package com.example.technopa




import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.technopa.Diet.Views.DietListFragment
import com.example.technopa.Fragments.ProfileFragment
import com.example.technopa.Trainings.Views.TrainingListFragment
import com.example.technopa.databinding.ActivityMainBinding
import com.example.technopa.databinding.ProfileLayoutBinding



class MainActivity : AppCompatActivity() {
    private val mainFragment = MainFragment()
    private val personalTrainingFragment = PersonalTrainingFragment()
    private val profileFragment = ProfileFragment()
    private var binding: ActivityMainBinding? = null



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        var fl:Boolean = true
        val view = binding?.root
        setContentView(view)
        binding?.BottomView?.selectedItemId = R.id.mainFragment

        binding?.BottomView?.setOnItemSelectedListener {
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