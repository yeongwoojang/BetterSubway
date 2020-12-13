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
            overridePendingTransition(R.anim.right_in,R.anim.left_out)
            finish()
        }

        sign_in_page_bt.setOnClickListener {
            startActivity(Intent(this@MainActivity,SignInActivity::class.java))
            overridePendingTransition(R.anim.right_in,R.anim.left_out)
            finish()
        }
    }
}