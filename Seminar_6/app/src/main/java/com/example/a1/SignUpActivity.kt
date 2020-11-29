package com.example.a1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import api.SampleServiceImpl
import data.LoginRequest
import data.LoginResponse
import data.SignUpRequest
import data.SignUpResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity() {

    //Login
    val REQUEST_CODE_LOGIN = 200

    private fun showError(error : ResponseBody?){
        val e = error ?: return
        val ob = JSONObject(e.string())
        Toast.makeText(this, ob.getString("message"),Toast.LENGTH_SHORT).show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        SignUpProcess()
    }

    //회원가입 시 프로세스 함수
    fun SignUpProcess() {
        sign_up.setOnClickListener{
            val call : Call<SignUpResponse> = SampleServiceImpl.service.postSignUp(
                SignUpRequest(
                    u_id.text.toString(),
                    u_pw.text.toString(),
                    u_name.text.toString()
                )
            )
            call.enqueue(object : Callback<SignUpResponse> {
                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                    // 통신 실패 로직
                }
                override fun onResponse(
                    call: Call<SignUpResponse>,
                    response: Response<SignUpResponse>
                ) {
                    response.takeIf { it.isSuccessful}
                        ?.body()
                        ?.let { it ->
                            //필수사항 입력칸 공백 체크
                            if(u_name.text.toString().equals("") ||u_id.text.toString().equals("") ||
                                u_pw.text.toString().equals("") || u_pw_c.text.toString().equals(""))
                                Toast.makeText(this@SignUpActivity, "빈칸을 입력해주세요!", Toast.LENGTH_SHORT).show()
                            //비밀번호 일치 여부 확인
                            else if(!u_pw.text.toString().equals(u_pw_c.text.toString()))
                                Toast.makeText(this@SignUpActivity, "비밀번호가 일치하지 않습니다!",Toast.LENGTH_SHORT).show()
                            else {
                                Toast.makeText(this@SignUpActivity,"회원가입 완료!",Toast.LENGTH_SHORT).show()
                                //MainActivity로 이동
                                val intent = Intent(this@SignUpActivity, MainActivity::class.java)

                                //MainActivity로 화면전환한 후 사용자가 입력한 아이디, 비밀번호 보이게 함
                                intent.putExtra("id",u_id.text.toString())
                                intent.putExtra("pw",u_pw.text.toString())
                                startActivityForResult(intent,REQUEST_CODE_LOGIN)
                            }
                        } ?: showError(response.errorBody())
                }
            }
            )
         //회원가입 원래 있던 자리
        }
    }
}