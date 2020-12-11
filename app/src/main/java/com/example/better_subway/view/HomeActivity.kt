package com.example.better_subway.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import com.example.better_subway.R
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        menu_bt.setOnClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
        }
        seat_info_bt.setOnClickListener {
            startActivity(Intent(this@HomeActivity,SeatInfoActivity::class.java))
        }
        nav_view.setNavigationItemSelectedListener {item->
            item.isChecked = true
            drawer_layout.closeDrawers()
            when(item.itemId){
                R.id.report ->  startActivity(Intent(this@HomeActivity,ReportActivity::class.java))    //신고페이지 이동
                R.id.logout ->   startActivity(Intent(this@HomeActivity,MainActivity::class.java))   //로그아웃진행
            }
            true
        }
    }
}