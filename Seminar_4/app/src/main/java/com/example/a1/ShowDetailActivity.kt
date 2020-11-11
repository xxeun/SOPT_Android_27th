package com.example.a1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_show_detail.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class ShowDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_detail)

        //RecyclerViewActivity에 있는 아이템 클릭 시 보여지는 상세정보
        //SampleAdapter에서 putExtra 이용해서 넘겼던 데이터를 getStringExtra 사용해서 받아옴. 키 값 이용
        var intent = intent
        val aTitle = intent.getStringExtra("iTitle")
        val aSubTitle = intent.getStringExtra("iSubTitle")
        val aWriteDay = intent.getStringExtra("iWriteDay")
        val aInfo = intent.getStringExtra("iInfo")

        /*
        design layout id.setText.
        앞서 getStringExtra로 받아왔던 데이터 값이 들어있는 변수를 넣어줌
        */
        detail_title.setText(aTitle)
        detail_subtitle.setText(aSubTitle)
        detail_writeDay.setText(aWriteDay)
        detail_info.setText(aInfo)
    }
}