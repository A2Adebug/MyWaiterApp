package com.example.mywaiterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_advert_list.*

class AdvertList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advert_list)

        val db = FirebaseFirestore.getInstance()
//        FirebaseFirestore.setLoggingEnabled(true);


        adListRecyclerView.adapter = AdvertListAdapter(mutableListOf())

        db.collection("events").get()
            .addOnSuccessListener { result ->
                // да, я говнишка, и вложил мапу в лист, чтобы не париться
                val dataSet: MutableList<MutableMap<String, String>> = arrayListOf()
                val i = 0
                for (advert in result) {
                    dataSet.add(advert.data as MutableMap<String, String>)
                }

                val adapter = AdvertListAdapter(dataSet)

                adListRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                adListRecyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
//                Log.d("Barmaley", dataSet.toString())
            }
    }
}