package com.example.better_subway.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import com.example.better_subway.R
import com.example.better_subway.view.activity.SeatInfoActivity
import com.example.better_subway.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    val viewModel by viewModels<HomeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        menu_bt.setOnClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
        }
        seat_info_bt.setOnClickListener {
            startActivity(
                Intent(
                    this@HomeActivity,
                    SeatInfoActivity::class.java
                )
            )
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
            finish()
        }
        my_page_bt.setOnClickListener {
            startActivity(Intent(this@HomeActivity, MyPageActivity::class.java))
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
            finish()
        }
        nav_view.setNavigationItemSelectedListener { item ->
            item.isChecked = true
            drawer_layout.closeDrawers()
            when (item.itemId) {
                R.id.logout -> {
                    viewModel.logout()
                    startActivity(
                        Intent(
                            this@HomeActivity,
                            MainActivity::class.java
                        )
                    )   //로그아웃 후 메인화면으로 이동
                    overridePendingTransition(R.anim.left_in, R.anim.right_out);
                    finish()
                }
            }
            true
        }
    }
}