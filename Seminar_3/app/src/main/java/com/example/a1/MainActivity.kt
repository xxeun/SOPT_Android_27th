package com.example.a1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_view_pager.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    var code = 1
    private lateinit var viewPagerAdapter: SampleViewPagerAdapter

    lateinit var pref:SharedPreferences
    lateinit var editor:SharedPreferences.Editor
    val REQUEST_CODE_SIGNUP = 100
    val REQUEST_CODE_LOGIN = 200
    var checkAutoLogIn = false
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
        Login.setOnClickListener{
            if(!(id.text.toString().equals("") || pw.text.toString().equals(""))){
                checkAutoLogIn = true
                //editor 사용해서 데이터 저장할 수 있음.
                editor.putBoolean("checkAutoLogin", checkAutoLogIn)
                editor.putString("id",id.text.toString())

                editor.apply()
                //반드시 apply 메서드 호출해줘야 실제 반영됨
                /*
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                 */
                startActivityForResult(Intent(this, RecyclerViewActivity::class.java), REQUEST_CODE_LOGIN)

                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this, "빈칸을 입력해주세요",Toast.LENGTH_SHORT).show()
        }
        autoLogIn()
    }

    //자동 로그인 구현 함수
    fun autoLogIn() {
        if(pref.getBoolean("checkAutoLogIn", false) == true || !pref.getString("id", null).isNullOrBlank()){
            Toast.makeText(this, "자동로그인 되었습니다!", Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext,RecyclerViewActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_LOGIN)
            /*val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)*/
        }

    }

    //회원가입창으로 화면 전환
    fun gotoSignUp() {
        SignUp.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_SIGNUP)
            //startActivity(intent)
        }
    }

    //회원가입할 때 입력한 데이터 보여줌
    fun ShowData() {
        if(intent.hasExtra("id")) {
            id.setText(intent.getStringExtra("id"))
        }
        if(intent.hasExtra("pw")) {
            pw.setText(intent.getStringExtra("pw"))
        }
    }
}