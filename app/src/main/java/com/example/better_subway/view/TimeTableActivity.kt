package com.example.better_subway.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.better_subway.R
import com.example.better_subway.model.vo.Arrival
import com.example.better_subway.view.adapter.LeftArrivalAdapter
import com.example.better_subway.view.adapter.RightArrivalAdapter
import com.example.better_subway.viewmodel.TimeTableViewModel
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_time_table.*

@AndroidEntryPoint
class TimeTableActivity : AppCompatActivity() {

    private val viewModel by viewModels<TimeTableViewModel>()
    private var arrivalList = arrayListOf<Arrival>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table)

        val intnet = intent
        if(intent!=null){
            arrivalList = intent.getSerializableExtra("arrivalList") as ArrayList<Arrival>
        }
        //가는 방향에 따라 지하철 도착정보를 나눈다.
        viewModel.divideTimeTable(arrivalList)

        slidingView.panelHeight=0
        //선택한 역 화면에 뿌려주기
        station_name.text = arrivalList[0].station

        val lefttAdapter = LeftArrivalAdapter(object : LeftArrivalAdapter.leftArrivalAdtListener{
            override fun request() {
                slidingView.panelState  = SlidingUpPanelLayout.PanelState.EXPANDED
            }
        })
        val rightAdapter = RightArrivalAdapter()
        left_recyclerView.apply {
            this.layoutManager = LinearLayoutManager(this@TimeTableActivity,RecyclerView.VERTICAL,false)
            this.adapter = lefttAdapter
        }
        right_recyclerView.apply {
            this.layoutManager = LinearLayoutManager(this@TimeTableActivity,RecyclerView.VERTICAL,false)
            this.adapter = rightAdapter
        }
        lefttAdapter.updateItems(viewModel.leftArrivalList)
        rightAdapter.updateItems(viewModel.rightArrivalList)
        //왼쪽 목적지 화면에 뿌려주기
        left_direction.text = viewModel.leftArrivalList[0].direction
        right_direction.text = viewModel.rightArrivalList[0].direction
    }
}