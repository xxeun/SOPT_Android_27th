package com.example.a1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    //Login
    val REQUEST_CODE_LOGIN = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        SignUpProcess()
    }

    //회원가입 시 프로세스 함수
    fun SignUpProcess() {
        sign_up.setOnClickListener{
            //필수사항 입력칸 공백 체크
            if(u_name.text.toString().equals("") ||u_id.text.toString().equals("") ||
                    u_pw.text.toString().equals("") || u_pw_c.text.toString().equals(""))
                Toast.makeText(this, "빈칸을 입력해주세요!", Toast.LENGTH_SHORT).show()
            //비밀번호 일치 여부 확인
            else if(!u_pw.text.toString().equals(u_pw_c.text.toString()))
                Toast.makeText(this, "비밀번호가 일치하지 않습니다!",Toast.LENGTH_SHORT).show()
            else {
                Toast.makeText(this,"회원가입 완료!",Toast.LENGTH_SHORT).show()
                //MainActivity로 이동
                val intent = Intent(this, MainActivity::class.java)

                //MainActivity로 화면전환한 후 사용자가 입력한 아이디, 비밀번호 보이게 함
                intent.putExtra("id",u_id.text.toString())
                intent.putExtra("pw",u_pw.text.toString())
                startActivityForResult(intent,REQUEST_CODE_LOGIN)
            }
        }
    }
}