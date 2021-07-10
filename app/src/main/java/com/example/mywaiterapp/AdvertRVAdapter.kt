package com.example.mywaiterapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdvertRVAdapter (private val tags: ArrayList<String>): RecyclerView.Adapter<AdvertRVAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val AdvertAdapterTV: TextView = view.findViewById<View>(R.id.AdvertAdapterTV) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertRVAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.advert_adapter_tag_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tag = tags[position]
        holder.AdvertAdapterTV.text = tag
    }

    override fun getItemCount(): Int {
        return tags.size
    }
}