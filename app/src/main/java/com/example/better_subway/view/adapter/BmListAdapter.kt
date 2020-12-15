package com.example.better_subway.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.better_subway.R
import com.example.better_subway.databinding.ItemBookmarkBinding
import com.example.better_subway.databinding.ItemLineBinding
import com.example.better_subway.model.vo.Arrival
import com.example.better_subway.model.vo.BookMark


class BmViewHoler(itemView : View) : RecyclerView.ViewHolder(itemView){
        val binding = ItemBookmarkBinding.bind(itemView)
}
class BmListAdapter(private val listener: BmListAdapter.BmListAdtlistener) : RecyclerView.Adapter<BmViewHoler>() {

    private var  mItems :List<BookMark> = ArrayList<BookMark>()
    fun updateItems(items: List<BookMark>) {
        mItems = items
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BmViewHoler {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_bookmark, parent, false)
        return BmViewHoler(view)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: BmViewHoler, position: Int) {
        val bookMark  :BookMark  = mItems[position]
        holder.binding.bookmark = mItems[position]
        holder.binding.bookmarkText.setOnClickListener {
            listener.request(bookMark.station)
        }
    }
    interface BmListAdtlistener{
        fun request(station : String)
    }
}