package com.example.better_subway.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.better_subway.model.vo.Arrival
import com.example.better_subway.model.vo.Line
import com.example.better_subway.model.vo.Station
import com.example.better_subway.model.vo.StationInfo
import com.example.better_subway.repository.ServiceApi
import kotlinx.coroutines.launch

class SeatInfoViewModel @ViewModelInject constructor(
    private val service: ServiceApi
) : ViewModel() {


    val stationLiveDate = MutableLiveData<StationInfo>()
    var arrivalLiveData = MutableLiveData<List<Arrival>>()
    val lineListLiveData = MutableLiveData<List<Line>>()
    val isSearchLiveData = MutableLiveData<Boolean>()

    fun getStationInfo(line: String) {
        viewModelScope.launch {
            val data = service.getStationInfo(line)
            stationLiveDate.value = data
            Log.d("TEST", "getStationInfo: ${data.jsonArray.toString()}")
        }

    }

    fun getArrivalTime(station: String) {
        viewModelScope.launch {
            val data = service.getArrivalTime(station)
            if (data.code == 404) {
                Log.d("TEST", "getArrivalTime: 서비스없음")
            } else {
                arrivalLiveData.value = data.jsonArray
                Log.d("TEST", "getArrivalTime: ${data.jsonArray}")
            }

        }
    }

    fun initList() {
        var lineList = ArrayList<Line>()
        for (i in 1..9) {
            lineList.add(Line("${i}호선", false))
        }
        lineListLiveData.value = lineList
    }

    fun tabUpdate(position: Int) {
        var lineList = ArrayList<Line>()
        for (i in 1..9) {
            if (i != position + 1) {
                lineList.add(Line("${i}호선", false))

            } else {
                lineList.add(Line("${i}호선", true))

            }
        }
        lineListLiveData.value = lineList
    }

    fun tabClear() {
        var lineList = ArrayList<Line>()
        for (i in 1..9) {
            lineList.add(Line("${i}호선", false))
        }
        lineListLiveData.value = lineList
    }

    fun searchStation(station: String) {
        viewModelScope.launch {
            var data = service.searchStation(station)
            stationLiveDate.value = data
            Log.d("Ssafs", "searchStation:${data.toString()} ")
        }
    }
}