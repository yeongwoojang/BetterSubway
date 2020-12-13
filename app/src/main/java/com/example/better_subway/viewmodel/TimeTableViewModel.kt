package com.example.better_subway.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.better_subway.model.vo.Arrival
import com.example.better_subway.model.vo.Seat
import com.example.better_subway.repository.ServiceApi
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class TimeTableViewModel @ViewModelInject constructor(
    private val service: ServiceApi
) : ViewModel() {

    val direction = arrayListOf<String>()

    var leftArrivalList = arrayListOf<Arrival>()
    var rightArrivalList = arrayListOf<Arrival>()

    val seatLiveData = MutableLiveData<List<Seat>>()


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

    //지하철 좌석정보를 불러오는 함수
    fun getSeatInfo(trainNum : Int,blockNum : String){
        viewModelScope.launch {
            val data = service.getSeatInfo(trainNum,blockNum)
            seatLiveData.value = data.jsonArray
            Log.d("TEST", "getSeatInfo: ${data.jsonArray}")
        }
    }







}