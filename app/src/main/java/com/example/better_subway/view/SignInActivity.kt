package com.example.better_subway.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.better_subway.R
import com.example.better_subway.viewmodel.SignInViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_signin.*

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {

    private val viewModel by viewModels<SignInViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        sign_in_bt.setOnClickListener {
            val userId = sigin_in_id_edt.text.toString().trim()
            val password = sign_in_password_edt.text.toString().trim()
            if(userId!="" && password!=""){
                viewModel.signIn(userId,password)
            }else{
                Snackbar.make(sign_in_layout,"정확히 입력해주세요.",Snackbar.LENGTH_LONG).show()
            }
        }
        go_to_main_bt.setOnClickListener {
            startActivity(Intent(this@SignInActivity,MainActivity::class.java))
            overridePendingTransition(R.anim.left_in,R.anim.right_out)
            finish()
        }

        viewModel.codeLiveData.observe(this, Observer { code->
            if(code=="200"){
                Snackbar.make(sign_in_layout,"로그인에 성공했습니다.",Snackbar.LENGTH_LONG).show()
                startActivity(Intent(this@SignInActivity,HomeActivity::class.java))
                overridePendingTransition(R.anim.right_in,R.anim.left_out)
                finish()
            }else{
                Snackbar.make(sign_in_layout,"로그인에 실패했습니다..",Snackbar.LENGTH_LONG).show()
            }

        })
    }
}