package com.example.a1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
//import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_recycler_view.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlin.properties.Delegates

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var viewPagerAdapter: SampleViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        viewPagerAdapter = SampleViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.fragments = listOf(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment()
        )

        sample_viewpager.adapter = viewPagerAdapter


        //tab이 변경되었을 때 setonlistener 추가하는 거??

        //뷰페이저를 슬라이드 했을 때 그에 대응되는 하단 탭 변경

        sample_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                sample_bottom_navi.menu.getItem(position).isChecked = true
            }
        })

        // 하단 탭을 눌렀을 때 뷰페이저 화면 변경

        sample_bottom_navi.setOnNavigationItemSelectedListener {
            var index by Delegates.notNull<Int>()
            when(it.itemId) {
                R.id.menu_Me -> index = 0 //인덱스 기준 첫번째 화면은 0
                R.id.menu_PortFolio -> index = 1
                R.id.menu_Setting -> index = 2
            }
            sample_viewpager.currentItem = index //하단 탭 눌렀을 때 그에 대응하는 viewPager 화면 보여지게 됨
            true//반환값이 boolean 값이기 때문에 true 전달
        }
    }
}