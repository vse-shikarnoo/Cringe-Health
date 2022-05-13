package com.example.technopa.diet.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.technopa.Dieta
import com.example.technopa.R
import com.example.technopa.databinding.DetailDietLayoutBinding
import com.example.technopa.withArguments

class DetailDietFragment: Fragment(R.layout.detail_diet_layout) {



    private var dieta: Dieta? = null

    private var binding: DetailDietLayoutBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DetailDietLayoutBinding.bind(view)

        bindInfo()

    }

    private fun bindInfo(){
        dieta = requireArguments().getParcelable(KEY_DIETA)
        binding!!.titleTextView.text = dieta?.title
        binding!!.kaloriipdTextView.text = "${dieta?.kaloriipd} ккал в день"
        binding!!.opisanieTextView.text = dieta?.opisanie
    }

    companion object {

        private const val KEY_DIETA = "key_dieta"

        fun newInstance(dieta: Dieta): DetailDietFragment {
            return DetailDietFragment().withArguments {
               putParcelable(KEY_DIETA,dieta)
            }
        }
    }
}