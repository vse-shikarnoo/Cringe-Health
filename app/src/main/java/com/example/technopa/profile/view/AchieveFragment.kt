package com.example.technopa.Profile.Views

import android.os.Bundle
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

    var achievments : List<String> = listOf("10 шагов пройдено","100 шагов пройдено",
        "500 шагов пройдено","1000 шагов пройдено","5000 шагов пройдено","10000 шагов пройдено")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = AchieveLayoutBinding.inflate(inflater, container,  false)

        binding.achieveRv.adapter = AchieveAdapter(achievments)
        binding.achieveRv.layoutManager = LinearLayoutManager(context)


        return binding.root
    }





}