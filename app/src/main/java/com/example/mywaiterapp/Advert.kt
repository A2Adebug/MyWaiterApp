package com.example.mywaiterapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_advert.*

class Advert : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advert)

        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        AdvertRVDesc.layoutManager = linearLayoutManager

        val tags = ArrayList<String>()
        tags.add("Требуется официант на выпускной банкет. Отель 'Президент'. Облив, обнос, форма строго классика, не джинсы и сапоги. Очень хочу видеть классных ребят, спасибо всем большое. Не знаю на самом деле что здесь ещё можно писать, просто нужно как - то заполнить пространство текстом. Путин вор. \n \nПунктики какие - то типа:\n1. Парабола\n2. Михаил\n3. Судороги\n4. Абэмэ\n 5. Просто херня какая - то, лишь бы текст для пролистки в ресайклере заполнить, тупо проверочка в общем говоря")

        val adapter = AdvertRVAdapter(tags)
        AdvertRVDesc.adapter = adapter
    }
}