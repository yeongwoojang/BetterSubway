package com.example.better_subway.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.better_subway.R
import com.example.better_subway.viewmodel.SignUpViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_sign_up.*

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    val viewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        save_bt.setOnClickListener {
            val userName = sign_up_name_edt.text.toString().trim()
            val userId = sign_up_id_edt.text.toString().trim()
            val password = sign_up_password_edt.text.toString().trim()
            if (userName != "" && userId != "" && password != "") {
                viewModel.signUp(userName,userId,password)
            } else {
                Snackbar.make(main_layout,"올바른 정보를 입력해주세요.",Snackbar.LENGTH_LONG).show()
            }
        }

        cancel_bt.setOnClickListener {
            startActivity(Intent(this@SignUpActivity,
                MainActivity::class.java))
            overridePendingTransition(R.anim.left_in,R.anim.right_out)
            finish()
        }

        viewModel.codeLiveData.observe(this, Observer { code->
            if(code=="200"){
                Snackbar.make(main_layout,"회원가입 성공",Snackbar.LENGTH_LONG).show()
                startActivity(Intent(this@SignUpActivity,
                    HomeActivity::class.java))
                overridePendingTransition(R.anim.right_in,R.anim.left_out)
                finish()
            }else{
                Snackbar.make(main_layout,"회원가입 실패",Snackbar.LENGTH_LONG).show()
                sign_up_name_edt.text.clear()
                sign_up_id_edt.text.clear()
                sign_up_password_edt.text.clear()

            }

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