package com.example.better_subway.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.better_subway.R
import com.example.better_subway.model.vo.Arrival
import com.example.better_subway.view.adapter.BmListAdapter
import com.example.better_subway.viewmodel.MyPageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_my_page.*

@AndroidEntryPoint
class MyPageActivity : AppCompatActivity() {

    private val viewModel by viewModels<MyPageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        viewModel.getBookMarkList()
        val adapter = BmListAdapter(object : BmListAdapter.BmListAdtlistener{
            override fun request(station : String) {
                viewModel.getArrivalTime(station)

            }
        })
        bm_recyclerView.apply {
            this.layoutManager = LinearLayoutManager(this@MyPageActivity, RecyclerView.VERTICAL, false)
            this.adapter = adapter
        }

        m_back_bt.setOnClickListener {
            startActivity(Intent(this@MyPageActivity,HomeActivity::class.java))
            overridePendingTransition(R.anim.left_in,R.anim.right_out)
            finish()
        }
        viewModel.bookMarkListLiveData.observe(this, Observer {bookmarkInfo->
            if(bookmarkInfo.code==200){
                Log.d("TEST", "onCreate: ${bookmarkInfo.jsonArray.toString()}")
                adapter.updateItems(bookmarkInfo.jsonArray)
            }

        })

        viewModel.arrivalLiveData.observe(this, Observer {
            val intent = Intent(this@MyPageActivity, TimeTableActivity::class.java)
            intent.putParcelableArrayListExtra("arrivalList", it as ArrayList<Arrival>)
            startActivity(intent)
            finish()
        })
    }
    override fun onBackPressed() {
        var tempTime = System.currentTimeMillis()
        var intervalTime = tempTime- HomeActivity.backPressedTime
        if(0<=intervalTime && HomeActivity.FINISH_INTERVAL_TIME >= intervalTime){
            super.onBackPressed()
        }else{
            HomeActivity.backPressedTime = tempTime
            Toast.makeText(applicationContext,"뒤로가기 버튼을 한번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
        }

    }
}