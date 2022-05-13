package com.example.technopa.diet.views

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technopa.diet.models.DietListModel
import com.example.technopa.Dieta
import com.example.technopa.interfaces.FragmentInterface
import com.example.technopa.R
import com.example.technopa.autoCleared
import com.example.technopa.databinding.DietListLayoutBinding
import com.example.technopa.toast

class DietListFragment : Fragment(R.layout.diet_list_layout) {

    private val viewModel: DietListModel by viewModels()

    private var binding: DietListLayoutBinding? = null

    private var dietListAdapter: DietListAdapter by autoCleared()

    private val fragmentInterface: FragmentInterface?
        get()=activity?.let{it as? FragmentInterface}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //toast("Диеты")
        super.onViewCreated(view, savedInstanceState)
        binding = DietListLayoutBinding.bind(view)

        init()
        observe()
        binding!!.swipeRefresh.setOnRefreshListener {
            viewModel.getDiets()
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    fun init() {
        dietListAdapter = DietListAdapter { position ->
            fragmentInterface?.onItemSelectedDiets(viewModel.dietList.value?.get(position)?: Dieta())
        }
        with(binding!!.recyclerViewDietList) {
            adapter = dietListAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        viewModel.getDiets()
    }

    private fun observe() {
        viewModel.dietList.observe(viewLifecycleOwner) { dietListAdapter.submitList(it) }
        viewModel.isLoading.observe(viewLifecycleOwner){
            updateLoadingState(it)
            binding!!.swipeRefresh.isRefreshing = false
        }
        viewModel.isError.observe(viewLifecycleOwner) { updateErrorState(it) }

    }

    private fun updateLoadingState(isLoading: Boolean) {
        binding!!.recyclerViewDietList.isVisible = isLoading.not()
        binding!!.progressBar.isVisible = isLoading
    }

    private fun updateErrorState(error: Throwable?) {
        if (error != null) {
            toast(error.message.toString())        }
    }
}