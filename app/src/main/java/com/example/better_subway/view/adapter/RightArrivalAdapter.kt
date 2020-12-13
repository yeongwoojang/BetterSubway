package com.example.better_subway.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.better_subway.R
import com.example.better_subway.databinding.ItemLeftArrivalBinding
import com.example.better_subway.databinding.ItemRightArrivalBinding
import com.example.better_subway.model.vo.Arrival
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class RightArrivalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = ItemRightArrivalBinding.bind(itemView)
}

class RightArrivalAdapter(val listener : RightArrivalAdtListener) : RecyclerView.Adapter<RightArrivalViewHolder>() {

    private var mItems: List<Arrival> = ArrayList<Arrival>()

    fun updateItems(items: List<Arrival>) {
        mItems = items;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RightArrivalViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_right_arrival, parent, false)
        return RightArrivalViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: RightArrivalViewHolder, position: Int) {
        val arrival : Arrival = mItems[position]
        holder.binding.arrival = mItems[position]
        holder.binding.train2Bt.setOnClickListener {
            listener.request(arrival.trainNum)
        }

    }
    interface RightArrivalAdtListener{
        fun request(trainNum : Int)
    }
}

@BindingAdapter("arrivalTime2")
fun setArrivalTime2(textView : TextView, arrival: Arrival){

    val calendar  = Calendar.getInstance()
    //임의의 현재시간 설정
    calendar.set(Calendar.HOUR_OF_DAY,19)
    calendar.set(Calendar.MINUTE,0)
    calendar.set(Calendar.SECOND,0)

    val timeFormat = SimpleDateFormat("HH:mm:ss")
    val resultFormat = SimpleDateFormat("mm")

    //받아온 도착시간을 Date형으로 변환
    val arrivalTime = timeFormat.parse(arrival.arrivalTime)
    val currentTime = Date(calendar.timeInMillis)

    //현재시간에서 도착시간까지 얼마나 남았는지 계산
    val result = resultFormat.format(arrivalTime.time).toInt() - resultFormat.format(currentTime.time).toInt()
    textView.text = "${result}분 후"
}
