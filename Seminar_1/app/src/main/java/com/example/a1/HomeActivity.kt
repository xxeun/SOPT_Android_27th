package com.example.a1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private lateinit var sampleAdapter: SampleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        sampleAdapter = SampleAdapter(this)

        view_rcv.adapter = sampleAdapter
        view_rcv.layoutManager = LinearLayoutManager(this)

        sampleAdapter.data = mutableListOf(
            SampleData("1","소개", "2020.10.18","남가은, 22살, 숙명여자대학교 소비자경제학과, IT공학전공"),
            SampleData("2","관심 학문","2020.10.18","IT전반 산업, 소비자학"),
            SampleData("3","깃허브","2020.10.18","안드로이드 초보의 깃허브 : https://github.com/xxeun")
        )
        sampleAdapter.notifyDataSetChanged()
    }
}