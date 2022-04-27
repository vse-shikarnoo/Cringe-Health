package com.example.technopa.PersonalDiet

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technopa.*
import com.example.technopa.databinding.PersonalDietLayoutBinding


class PersonalDietFragment : Fragment(R.layout.personal_diet_layout) {





    private var binding: PersonalDietLayoutBinding? = null

    private val listEda = mutableListOf(
        Eda("Syrok",kalorii = 102.0, belki = 25.0, zhiri = 30.0, uglevodi = 10.0),
        Eda("Sup",kalorii = 112.0, belki = 35.0, zhiri = 40.0, uglevodi = 20.0),
        Eda("Meat",kalorii = 122.0, belki = 45.0, zhiri = 50.0, uglevodi = 30.0)
    )
    private val list:MutableList<PriemPishi> = mutableListOf(
        PriemPishi("Завтрак",listEda),
        PriemPishi("Обед",listEda),
        PriemPishi("Ужин",listEda)
    )

    private var adapterPersonalDiet:PersonalDietAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PersonalDietLayoutBinding.bind(view)

        init()



        binding!!.buttonPersonalDietAdd.setOnClickListener {
            //addDialog()
            //myRef.setValue(list.random())


            /*
            -MzWWDzFrpiD9ZSoFryh
                Завтрак
                    eda: "Sirok"
                    title: "Завтрак"

             */
            //mRf.child(list[0].title).setValue(list[0])
            /*
            Завтрак
                eda: "Sirok"
                title: "Завтрак"
             */
            //mRf.child(list[0].title).setValue(list[1])
            //mrfUsers.child("124").setValue("Vanya")
            val userList:List<User> = listOf(
                User(1,"caramel", 163.0, 69.6, ),
                User(2,"Vanya", 173.0, 79.6, ),
                User(3,"Vlad", 193.0, 89.6, )
            )
            val user = userList.random()
            FirebaseNetwork().setUser(userList.random())
            FirebaseNetwork().getDiets({},{})

        }
    }

    fun init(){
        adapterPersonalDiet = PersonalDietAdapter()
        with(binding!!.recyclerViewPersonalDiet){
            adapter = adapterPersonalDiet
            layoutManager = GridLayoutManager(requireContext(),3)
            setHasFixedSize(true)
        }
        adapterPersonalDiet.submitList(list)
    }


    fun addDialog(){
        AlertDialog.Builder(requireContext())
            .setMessage("Проводим технические работы по добавлению добавления")
            .setTitle("Не работает")
            .setPositiveButton("Понял",{_ , _ ->})
            .setNegativeButton("Не понял",{_ , _ ->addDialog()})
            .show()
    }



}