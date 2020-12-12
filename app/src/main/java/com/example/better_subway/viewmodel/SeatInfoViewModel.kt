package com.example.better_subway.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.better_subway.model.vo.Arrival
import com.example.better_subway.model.vo.Station
import com.example.better_subway.repository.ServiceApi
import kotlinx.coroutines.launch

class SeatInfoViewModel @ViewModelInject constructor(
    private val service: ServiceApi
) : ViewModel() {


    val stationLiveDate = MutableLiveData<List<Station>>()
    var arrivalLiveData = MutableLiveData<List<Arrival>>()
    val lineList = ArrayList<String>()

    fun getStationInfo() {
        viewModelScope.launch {
            val data = service.getStationInfo()
            stationLiveDate.value = data.jsonArray
            Log.d("TEST", "getStationInfo: ${data.jsonArray.toString()}")

        }

    }

    fun getArrivalTime(station : String){
        viewModelScope.launch {
            val data = service.getArrivalTime(station)
            if(data.code==404){
                Log.d("TEST", "getArrivalTime: 서비스없음")
            }else{
                arrivalLiveData.value = data.jsonArray
                Log.d("TEST", "getArrivalTime: ${data.jsonArray}")
            }

        }
    }

    fun initList(): ArrayList<String> {
        lineList.add("1호선")
        lineList.add("2호선")
        lineList.add("3호선")
        lineList.add("4호선")
        lineList.add("5호선")
        lineList.add("6호선")
        lineList.add("7호선")
        lineList.add("8호선")
        return lineList
    }
}