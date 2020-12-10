package com.example.better_subway.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.better_subway.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sign_up_page_bt.setOnClickListener {
            startActivity(Intent(this@MainActivity,SignUpActivity::class.java))
        }

        sign_in_page_bt.setOnClickListener {
            startActivity(Intent(this@MainActivity,SignInActivity::class.java))
        }
    }
}