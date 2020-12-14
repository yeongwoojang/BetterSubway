package com.example.better_subway.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.better_subway.R
import com.example.better_subway.model.vo.Arrival
import com.example.better_subway.view.adapter.LineListAdapter
import com.example.better_subway.view.adapter.StationListAdpater
import com.example.better_subway.viewmodel.SeatInfoViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_seat_info.*

@AndroidEntryPoint
class SeatInfoActivity : AppCompatActivity() {


    val viewModel by viewModels<SeatInfoViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_info)

        var isSearch = false

        val adapter1 = LineListAdapter(this, object : LineListAdapter.LineListener {
            override fun request(position: Int, line: String) {
                sub_recyclerView.scrollToPosition(0)
                viewModel.tabUpdate(position)
                viewModel.getStationInfo("0${line}")

            }
        })
        val adapter2 = StationListAdpater(object : StationListAdpater.StationListener {
            override fun request(station: String) {
                viewModel.getArrivalTime(station)
            }
        })
        viewModel.initList()
        viewModel.lineListLiveData.observe(this, Observer {
            adapter1.updateItems(it)
        })


        recyclerview.apply {
            this.layoutManager =
                LinearLayoutManager(this@SeatInfoActivity, RecyclerView.HORIZONTAL, false)
            this.adapter = adapter1
        }
        sub_recyclerView.apply {
            this.layoutManager =
                LinearLayoutManager(this@SeatInfoActivity, RecyclerView.VERTICAL, false)
            this.adapter = adapter2
        }

        search_bt.setOnClickListener {
            val station = search_edt.text.toString().trim()
            if (station != "") {
                isSearch = true
                viewModel.searchStation(station)
            } else {
                Snackbar.make(seat_info_layout, "검색어를 입력해주세요.", Snackbar.LENGTH_SHORT).show()
            }
        }
        viewModel.stationLiveDate.observe(this, Observer {
            if (it.code == 200) {
                adapter2.updateItems(it.jsonArray)
                if(isSearch){
                search_edt.text.clear()
                viewModel.tabClear()
                }
                isSearch = false
            } else {
                Snackbar.make(seat_info_layout, "해당 역을 찾을 수 없습니다.", Snackbar.LENGTH_SHORT).show()
            }
        })

        viewModel.arrivalLiveData.observe(this, Observer {
            val intent = Intent(this@SeatInfoActivity, TimeTableActivity::class.java)
            intent.putParcelableArrayListExtra("arrivalList", it as ArrayList<Arrival>)
            startActivity(intent)
        })
    }
}