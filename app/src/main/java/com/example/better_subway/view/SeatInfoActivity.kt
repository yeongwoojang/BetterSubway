package com.example.better_subway.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.better_subway.R
import com.example.better_subway.model.vo.Arrival
import com.example.better_subway.view.adapter.LineListAdapter
import com.example.better_subway.view.adapter.StationListAdpater
import com.example.better_subway.viewmodel.SeatInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_seat_info.*

@AndroidEntryPoint
class SeatInfoActivity : AppCompatActivity() {

    val viewModel by viewModels<SeatInfoViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_info)

        val adapter1 = LineListAdapter(object : LineListAdapter.LineListener{
            override fun request() {
                Log.d("TEST", "request: Button is Clicked...")
                viewModel.getStationInfo()
            }
        })
        val adapter2 = StationListAdpater(object  : StationListAdpater.StationListener{
            override fun request(station : String) {
                viewModel.getArrivalTime(station)
            }
        })

        adapter1.updateItems(viewModel.initList())

        recyclerview.apply{
            this.layoutManager = LinearLayoutManager(this@SeatInfoActivity,RecyclerView.HORIZONTAL,false)
            this.adapter = adapter1
        }
        sub_recyclerView.apply {
            this.layoutManager = LinearLayoutManager(this@SeatInfoActivity,RecyclerView.VERTICAL,false)
            this.adapter = adapter2
        }

        viewModel.stationLiveDate.observe(this, Observer {
            adapter2.updateItems(it)
        })

        viewModel.arrivalLiveData.observe(this, Observer {
            val intent = Intent(this@SeatInfoActivity,TimeTableActivity::class.java)
            intent.putParcelableArrayListExtra("arrivalList",it as ArrayList<Arrival>)
            startActivity(intent)
        })
    }
}