package com.example.better_subway.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.better_subway.model.vo.Arrival
import com.example.better_subway.repository.ServiceApi
import java.text.SimpleDateFormat
import java.util.*

class TimeTableViewModel @ViewModelInject constructor(
    private val service: ServiceApi
) : ViewModel() {

    val direction = arrayListOf<String>()

    var leftArrivalList = arrayListOf<Arrival>()
    var rightArrivalList = arrayListOf<Arrival>()



    //   지하철 도착정보를 방향에 따라 나누는 함수
    fun divideTimeTable(arrivalList: List<Arrival>) {

        //선택한 역의 역코드
        val divider = arrivalList[0].sCode

        //선택한 역의 역코드를 기준으로 화면 전역 다음역을 구분하는 loop
        for (arrival in arrivalList) {
            if(arrival.dSCode>divider){
                leftArrivalList.add(arrival)
            }else{
                rightArrivalList.add(arrival)
            }
        }

        Log.d("MYMY", "divideTimeTable: ${leftArrivalList.toString()}")
        Log.d("MYMY", "divideTimeTable: ${rightArrivalList.toString()}")

    }
}