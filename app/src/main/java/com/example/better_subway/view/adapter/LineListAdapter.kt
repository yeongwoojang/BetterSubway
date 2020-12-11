package com.example.better_subway.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.better_subway.R
import com.example.better_subway.databinding.ItemLineBinding

class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    val binding = ItemLineBinding.bind(itemView)
}
class LineListAdapter(private var listener : LineListener) : RecyclerView.Adapter<CustomViewHolder>(){

    private var mItems : List<String> = ArrayList<String>()
    fun updateItems(items : List<String>){
        mItems = items;
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_line, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.binding.line = mItems[position]

        holder.binding.lineBt.setOnClickListener {
            listener.request()
        }

    }
    interface LineListener{
        fun request()
    }
}