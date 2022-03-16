package com.example.technopa.PersonalDiet

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technopa.MainClasses.Food
import com.example.technopa.R
import com.example.technopa.autoCleared
import kotlinx.android.synthetic.main.personal_diet_layout.*

//Экран Персонального плана питания
//По нажатию на элемент списка, добавляется еда
//По нажатию на кнопку добавляется прием пищи
//Отображения графика
class PersonalDietFragment : Fragment(R.layout.personal_diet_layout) {

    private val list: MutableList<PriemPishi> = mutableListOf(
        PriemPishi(
            "Завтрак",
            mutableListOf(
                Food(title = "Сырок", kkal = 100.0, b = 10.9, zh = 10.9, u = 10.9),
                Food(title = "Мясо", kkal = 100.0, b = 12.9, zh = 12.9, u = 12.9)
            )
        ),
        PriemPishi(
            "Обед",
            mutableListOf(
                Food(title = "Суп", kkal = 100.0, b = 11.9, zh = 11.9, u = 11.9)
            )
        ),
        PriemPishi(
            "Ужин",
            mutableListOf(
                Food(title = "Мясо", kkal = 100.0, b = 12.9, zh = 12.9, u = 12.9)
            )
        )
    )

    private var adapterPersonalDiet: PersonalDietAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        //Добавить прием пищи
        buttonPersonalDietAdd.setOnClickListener {
            addDialog()
        }

        //Подкорректировать это
        PersonalDietConcludeTextView.text = buildString {
            var allKkal: Double = 0.0
            var allB: Double = 0.0
            var allZh: Double = 0.0
            var allU: Double = 0.0
            list.forEach {
                it.contain.forEach {
                    allKkal = allKkal + it.kkal
                    allB = allB + it.b
                    allZh = allZh + it.zh
                    allU = allU + it.u
                }
            }
            append("Kкал : $allKkal\nБелки : $allB\nЖиры : $allZh\nУглеводы : $allU")
        }
    }


    fun init() {
        adapterPersonalDiet = PersonalDietAdapter(){

            //Открыть экран добавления еды
            list[it].contain.add(Food(title = "000", kkal = 0.0, b = 0.0, zh = 0.0, u = 0.0))
            adapterPersonalDiet.notifyItemChanged(it)
            Log.d("test",list[it].toString())
        }
        with(recyclerViewPersonalDiet) {
            adapter = adapterPersonalDiet
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        adapterPersonalDiet.submitList(list)
    }

    //Потом изменить
    fun addDialog() {
        AlertDialog.Builder(requireContext())
            .setMessage("Проводим технические работы по добавлению добавления")
            .setTitle("Не работает")
            .setPositiveButton("Понял", { _, _ -> })
            .setNegativeButton("Не понял", { _, _ -> addDialog() })
            .show()
    }


}