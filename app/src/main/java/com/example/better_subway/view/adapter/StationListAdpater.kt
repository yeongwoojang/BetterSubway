package com.example.better_subway.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.better_subway.R
import com.example.better_subway.databinding.ItemStationBinding
import com.example.better_subway.model.vo.Station

class StationViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    val binding = ItemStationBinding.bind(itemView)
}
class StationListAdpater(var listener : StationListener) :RecyclerView.Adapter<StationViewHolder>(){

    private var mItems :List<Station> = ArrayList<Station>()

    fun updateItems(items : List<Station>){
        mItems = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_station, parent, false)
        return StationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        holder.binding.station = mItems[position]
        holder.binding.stationBt.setOnClickListener {
            listener.request(holder.binding.stationName.text.toString())
        }
    }
    interface StationListener{
        fun request(station : String)
    }
}