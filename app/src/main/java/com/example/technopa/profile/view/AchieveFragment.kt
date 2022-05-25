package com.example.technopa.Profile.Views

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technopa.Profile.Models.AchieveAdapter
import com.example.technopa.R
import com.example.technopa.databinding.AchieveLayoutBinding
import com.example.technopa.databinding.AchievmentBinding


class AchieveFragment: Fragment() {

    val achievments : List<String> = listOf("10 шагов пройдено","100 шагов пройдено",
        "500 шагов пройдено","1000 шагов пройдено","5000 шагов пройдено","10000 шагов пройдено")

    private val APP_PREFERENCES_ACHIEVMENTS ="achievments"

    var achievementString: String = "000000"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = AchieveLayoutBinding.inflate(inflater, container,  false)

        achievementString =
            activity?.getSharedPreferences(R.string.APP_PREFERENCES.toString(), Application.MODE_PRIVATE)?.getString(
                APP_PREFERENCES_ACHIEVMENTS,""
            ).toString()

        Log.d("AchievmentString", "get $achievementString")

        activity?.getSharedPreferences(R.string.APP_PREFERENCES.toString(), Application.MODE_PRIVATE)?.edit()
            ?.putString(APP_PREFERENCES_ACHIEVMENTS,achievementString)
            ?.apply()

        binding.achieveRv.adapter = AchieveAdapter(achievments,achievementString)
        binding.achieveRv.layoutManager = LinearLayoutManager(context)


        return binding.root
    }





}