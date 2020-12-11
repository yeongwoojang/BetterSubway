package com.example.better_subway.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.better_subway.model.vo.Station
import com.example.better_subway.repository.ServiceApi
import kotlinx.coroutines.launch

class SeatInfoViewModel @ViewModelInject constructor(
    private val service: ServiceApi
) : ViewModel() {


    val stationLiveDate = MutableLiveData<List<Station>>()
    val lineList = ArrayList<String>()

    fun getStationInfo() {
        viewModelScope.launch {
            val data = service.getStationInfo()
            stationLiveDate.value = data.jsonArray
            Log.d("TEST", "getStationInfo: ${data.jsonArray.toString()}")

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