package com.example.mywaiterapp

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class AdvertListAdapter(private val dataSet:MutableList<MutableMap<String, String>>): RecyclerView.Adapter<AdvertListAdapter.ViewHolder>(){
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvId: TextView
        val tvType: TextView
        val tvDate: TextView
        val tvName: TextView
        val tvReward: TextView
        val tvAddress: TextView
        val tvDesc: TextView

        init {
            tvId = view.findViewById(R.id.AdListTVId)
            tvType = view.findViewById(R.id.AdListTVType)
            tvDate = view.findViewById(R.id.AdListTVDate)
            tvName = view.findViewById(R.id.AdListTVName)
            tvReward = view.findViewById(R.id.AdListTVReward)
            tvAddress = view.findViewById(R.id.AdListTVAddress)
            tvDesc = view.findViewById(R.id.AdListTVDesc)
            view.setOnClickListener {
                val intent = Intent(view.context, Advert::class.java).putExtra("id", tvId.text.toString())
                startActivity(view.context, intent, null)

                Log.d("AAA", tvId.text.toString())
            }
        }
    }

    // Создаем новые элементы листа
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.advert_list_adapter_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Заменяем данные в каждом элементе
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.tvId.text = dataSet[position]["id"]

        viewHolder.tvType.text = dataSet[position]["type"]
        val date = dataSet[position]["time"] + "|" + dataSet[position]["date"]
        viewHolder.tvDate.text = date

        viewHolder.tvName.text = dataSet[position]["name"]
        val reward = dataSet[position]["pay"] + "₽"
        viewHolder.tvReward.text = reward

        viewHolder.tvAddress.text = dataSet[position]["loc"]

        viewHolder.tvDesc.text = dataSet[position]["desc"]?.replace("\\n", System.getProperty("line.separator") as String)
    }

    override fun getItemCount(): Int = dataSet.size
}