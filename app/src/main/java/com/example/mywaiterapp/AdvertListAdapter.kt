package com.example.mywaiterapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdvertListAdapter(private val dataSet:MutableList<MutableMap<String, String>>): RecyclerView.Adapter<AdvertListAdapter.ViewHolder>(){
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvType: TextView
        val tvDate: TextView
        val tvName: TextView
        val tvReward: TextView
        val tvAddress: TextView
        val tvDesc: TextView

        init {
            // сюда листенер на элемент листа можно пихнуть
            tvType = view.findViewById(R.id.AdListTVType)
            tvDate = view.findViewById(R.id.AdListTVDate)
            tvName = view.findViewById(R.id.AdListTVName)
            tvReward = view.findViewById(R.id.AdListTVReward)
            tvAddress = view.findViewById(R.id.AdListTVAddress)
            tvDesc = view.findViewById(R.id.AdListTVDesc)
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
        viewHolder.tvType.text = dataSet[position]["type"]
        val date = dataSet[position]["time"] + "|" + dataSet[position]["date"]
        viewHolder.tvDate.text = date

        viewHolder.tvName.text = dataSet[position]["name"]
        val reward = dataSet[position]["reward"] + "₽"
        viewHolder.tvReward.text = reward

        viewHolder.tvAddress.text = dataSet[position]["address"]


        viewHolder.tvDesc.text = dataSet[position]["description"]?.replace("\\n", System.getProperty("line.separator") as String)
    }

    override fun getItemCount(): Int = dataSet.size
}