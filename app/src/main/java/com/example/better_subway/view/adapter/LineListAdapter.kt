package com.example.better_subway.view.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.better_subway.R
import com.example.better_subway.databinding.ItemLineBinding
import com.example.better_subway.model.vo.Arrival
import com.example.better_subway.model.vo.Line


class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    val binding = ItemLineBinding.bind(itemView)
}
class LineListAdapter(private val context : Context, private var listener : LineListener) : RecyclerView.Adapter<CustomViewHolder>(){
    val noClickColor = ContextCompat.getColor(context, R.color.white)
    var clickedItem :String = ""
    var clickedPosition = -1
    private var mItems : List<Line> = ArrayList<Line>()
    fun updateItems(items : List<Line>){
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
        val line : Line = mItems[position]

        holder.binding.line = mItems[position]

        holder.binding.lineBt.setOnClickListener {
            if(!line.isClicked){
                listener.request(position)
            }
        }

    }
    interface LineListener{
        fun request(pos : Int)
    }
}
@BindingAdapter("tabColor")
fun changeColor(button : Button, line : Line){
    when(!line.isClicked){
        false->{
            button.setTextColor(Color.BLACK)
        }
        true->{
            button.setTextColor(Color.WHITE)
        }
    }
}