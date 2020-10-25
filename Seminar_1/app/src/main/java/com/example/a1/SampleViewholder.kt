package com.example.a1

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SampleViewholder (itemView : View) : RecyclerView.ViewHolder(itemView) {

    private val title : TextView = itemView.findViewById(R.id.item_title)
    private val subTitle : TextView = itemView.findViewById(R.id.item_subtitle)
    //private val writeDay : TextView = itemView.findViewById(R.id.item_day)
    //private val info : TextView = itemView.findViewById(R.id.item_info)

    fun onBind(data : SampleData) {
        title.text = data.title
        subTitle.text = data.subTitle
        //writeDay.text = data.writeDay
        //info.text = data.info
    }
}
