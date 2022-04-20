package com.example.technopa.Diet.Views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.technopa.Dieta
import com.example.technopa.R
import com.example.technopa.databinding.DetailDietLayoutBinding
import com.example.technopa.databinding.DietListLayoutBinding

class DetailDietFragment: Fragment(R.layout.detail_diet_layout) {

    private val args: DetailDietFragmentArgs by navArgs()

    private var dieta: Dieta? = null

    private var binding: DetailDietLayoutBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DetailDietLayoutBinding.bind(view)

        bindInfo()

    }

    fun bindInfo(){
        dieta = args.dieta
        binding!!.titleTextView.text = dieta?.title
        binding!!.kaloriipdTextView.text = "${dieta?.kaloriipd} ккал в день"
        binding!!.opisanieTextView.text = dieta?.opisanie
    }
}