package com.example.better_subway.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.better_subway.databinding.ItemLineBinding


class BmViewHoler(itemView : View) : RecyclerView.ViewHolder(itemView){
    val binding = ItemLineBinding.bind(itemView)
}
class BmListAdapter : RecyclerView.Adapter<BmViewHoler>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BmViewHoler {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: BmViewHoler, position: Int) {
        TODO("Not yet implemented")
    }
}