package com.example.better_subway.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.better_subway.model.vo.Arrival
import com.example.better_subway.model.vo.BookMark
import com.example.better_subway.model.vo.BookMarkInfo
import com.example.better_subway.repository.ServiceApi
import kotlinx.coroutines.launch

class MyPageViewModel @ViewModelInject constructor(
    private val service : ServiceApi
): ViewModel(){


    val bookMarkListLiveData = MutableLiveData<BookMarkInfo>()
    var arrivalLiveData = MutableLiveData<List<Arrival>>()

    fun getBookMarkList(){
        viewModelScope.launch {
           val data = service.getBookMarkList()
            bookMarkListLiveData.value = data
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
}