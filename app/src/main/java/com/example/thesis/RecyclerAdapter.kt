package com.example.thesis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView

class RecyclerAdapter(private val historyList: ArrayList<History>, private val historyValue: ArrayList<HistoryValue>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var history = historyList[position]
//        holder.date.text = history.date
//        holder.time.text = history.date[4].toString()
//        var historyValue = historyValue[position]
//        holder.value.text = historyValue.value.toString()
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var date: MaterialTextView
        var time: MaterialTextView
        var value: MaterialTextView

        init {
            date = itemView.findViewById(R.id.date)
            time = itemView.findViewById(R.id.time)
            value = itemView.findViewById(R.id.value)
        }
    }

}