package com.example.better_subway.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.better_subway.R
import kotlinx.android.synthetic.main.activity_signin.*

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        sign_in_bt.setOnClickListener {
            startActivity(Intent(this@SignInActivity,HomeActivity::class.java))
        }
    }
}