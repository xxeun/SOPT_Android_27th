package com.example.a1

import Adapter.SampleViewPagerAdapter
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.BaseAdapter
import android.widget.Toast
import api.SampleServiceImpl
import data.SampleRequestData
import data.SampleResponseData
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {
    //var code = 1
    //private lateinit var viewPagerAdapter: SampleViewPagerAdapter

    lateinit var pref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    private fun showError(error : ResponseBody?){
        val e = error ?: return
        val ob = JSONObject(e.string())
        Toast.makeText(this, ob.getString("message"),Toast.LENGTH_SHORT).show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        val fragment1 = FirstFragment()
        val fragment2 = SecondFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment1).commit()

        change_btn.setOnClickListener{
            val transAction =  supportFragmentManager.beginTransaction()
            when(code){
                1 -> {
                    transAction.replace(R.id.fragment_container,fragment2)
                    code = 2
                }
                2 -> {
                    transAction.replace(R.id.fragment_container,fragment1)
                    code = 1
                }
            }
            transAction.commit()
        }
        */

        pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
        editor = pref.edit()

        logIn()
        gotoSignUp()
        ShowData()
    }


    fun logIn() {
        Login.setOnClickListener {
            val email = id.text.toString()
            val password = pw.text.toString()
            val call : Call<SampleResponseData> = SampleServiceImpl.service.postLogin(
                SampleRequestData(email = email, password = password)
            )
            call.enqueue(object : Callback<SampleResponseData>{
                override fun onFailure(call: Call<SampleResponseData>, t: Throwable) {
                    // 통신 실패 로직
                }
                override fun onResponse(
                    call: Call<SampleResponseData>,
                    response: Response<SampleResponseData>
                ) {
                    response.takeIf { it.isSuccessful}
                        ?.body()
                        ?.let { it ->
                        // do something
                        } ?: showError(response.errorBody())
                }
            }
            )

            if (!(id.text.toString().equals("") || pw.text.toString().equals(""))) {
                checkAutoLogIn = true
                //editor 사용해서 데이터 저장할 수 있음.
                editor.putBoolean("checkAutoLogin", checkAutoLogIn)
                editor.putString("id", id.text.toString())

                editor.apply()
                //반드시 apply 메서드 호출해줘야 실제 반영됨
                /*
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                 */
                startActivityForResult(
                    Intent(this, RecyclerViewActivity::class.java),
                    REQUEST_CODE_LOGIN
                )

                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(this, "빈칸을 입력해주세요", Toast.LENGTH_SHORT).show()
        }
        autoLogIn()
    }

    //자동 로그인 구현 함수
    fun autoLogIn() {
        if (pref.getBoolean("checkAutoLogIn", false) == true || !pref.getString("id", null)
                .isNullOrBlank()
        ) {
            Toast.makeText(this, "자동로그인 되었습니다!", Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext, RecyclerViewActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_LOGIN)
            /*val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)*/
        }

    }

    //회원가입창으로 화면 전환
    fun gotoSignUp() {
        SignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_SIGNUP)
            //startActivity(intent)
        }
    }

    //회원가입할 때 입력한 데이터 보여줌
    fun ShowData() {
        if (intent.hasExtra("id")) {
            id.setText(intent.getStringExtra("id"))
        }
        else
        {}

        if (intent.hasExtra("pw")) {
            pw.setText(intent.getStringExtra("pw"))
        }
    }

    companion object {
       const val REQUEST_CODE_SIGNUP = 100
       const val REQUEST_CODE_LOGIN = 200
        var checkAutoLogIn = false
    }
}