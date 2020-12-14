package com.example.better_subway.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.better_subway.R
import com.example.better_subway.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel by viewModels<MainViewModel>()

        val userId = viewModel.getLoginSession()
        if(userId!=" "){
             startActivity(Intent(this,
                 HomeActivity::class.java))
            overridePendingTransition(R.anim.right_in,R.anim.left_out)
            finish()
        }
        sign_up_page_bt.setOnClickListener {
            startActivity(Intent(this@MainActivity,
                SignUpActivity::class.java))
            overridePendingTransition(R.anim.right_in,R.anim.left_out)
            finish()
        }

        sign_in_page_bt.setOnClickListener {
            startActivity(Intent(this@MainActivity,
                SignInActivity::class.java))
            overridePendingTransition(R.anim.right_in,R.anim.left_out)
            finish()
        }
    }
}