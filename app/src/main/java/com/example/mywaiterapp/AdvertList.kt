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
                for (advert in result) {
                    val data = advert.data
                    // короче надо заменить id, тупа патаму чта лень менять в файерсторе, убери эту строку потом плез !!!!!!!!!!
                    data["id"] = advert.id
                    dataSet.add(data as MutableMap<String, String>)
                }

                val adapter = AdvertListAdapter(dataSet)

                adListRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                adListRecyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
                Log.d("Barmaley", dataSet.toString())
            }
    }
}