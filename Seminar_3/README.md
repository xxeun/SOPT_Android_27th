# SOPT_Android_27th_Seminar3

<div>
	<img src="https://user-images.githubusercontent.com/46614405/97841821-eb17c400-1d29-11eb-8021-c0f2969d40d7.gif", height=500>
	<img src="https://user-images.githubusercontent.com/46614405/97841847-f66aef80-1d29-11eb-9009-882e0f8bd141.gif", height=500>
	<img src="https://user-images.githubusercontent.com/46614405/97841885-071b6580-1d2a-11eb-9e8b-fc99036e23c5.gif", height=500>
</div>

> 3차 세미나 필수과제
>> 과제 완료일 2020.11.01, 리드미 작성일 2020.11.02
------------

## Fragment ##
* 하나의 액티비티가 여러 개의 화면을 가지도록 만들어줌 
* (Activity는 안드로이드 시스템이 관리, Fragment는 Activity가 관리 -> FragmentManager가 필요함)
## ViewPager ##
* 하나의 화면 안에서 여러가지 화면을 슬라이드 형식으로 보여줄 때 사용하며, 주로 하단 탭, 상단 탭과 연동하여 사용 -> 여러가지 화면은 Fragment로 구현
* Adapter와 연결해서 구현. ViewPager의 Adapter는 FragmentManager가 필요함
## BottomNavigation ##
ViewPager와 연동하여 서브 화면들을 전환. ViewPager에 페이지 변경에 관한 리스너가 필요함
## TabLayout ##
상단탭. setupWithViewPager 활용해서 TabLayout에 ViewPager 연동

------------
* Fragment 안에서 또 한 번 ViewPager 구현할 때는 OnViewCreated() 안에서 작업해줘야 함(UI 작업 등)
-----------
# 필수과제 #
> RecyclerViewActivity

	 viewPagerAdapter = SampleViewPagerAdapter(supportFragmentManager)
	 viewPagerAdapter.fragments = listOf(
	 	FirstFragment(),
            	SecondFragment(),
            	ThirdFragment()
		
* ViewPagerAdapter를 supportFragmentManager 사용해서 연동 후, RecyclerView에 들어갈 Fragment 3가지를 listOf로 연결

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

* 뷰페이저를 슬라이드 했을 때 그에 대응되는 하단 탭 변경

        sample_bottom_navi.setOnNavigationItemSelectedListener {
            var index by Delegates.notNull<Int>()
            when(it.itemId) {
                R.id.menu_home -> index = 0 //인덱스 기준 첫번째 화면은 0
                R.id.menu_account -> index = 1
                R.id.menu_camera -> index = 2
            }
            sample_viewpager.currentItem = index //하단 탭 눌렀을 때 그에 대응하는 viewPager 화면 보여지게 됨
            true//반환값이 boolean 값이기 때문에 true 전달
	   
* 하단 탭을 눌렀을 때 뷰페이저 화면 변경. 하단 탭의 icon id에 따른 index를 지정해줌
* when문, itemId 사용

-----------------
> FragmentViewpagerAdapter

 	: FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    	var fragments = listOf<Fragment>()
    	override fun getItem(position: Int): Fragment = fragments[position]
    	override fun getCount(): Int = fragments.size //크기 반환
	
* ViewPager마다 Adapter가 별도로 필요함. 
* 3차 세미나 과제에서는 첫 번째 Fragment에서 ViewPager를 하나 더 만들어야 하는 것! 따라서, 첫 번째 Fragment에 대한 Adapter가 필요함
---------------------

> FirstFragment

 	fragmentViewpagerAdapter = FragmentViewpagerAdapter(childFragmentManager)
        fragmentViewpagerAdapter.fragments = listOf(
            InfoFragment(),
            OtherFragment()
        )

* RecyclerViewActivity에서 First, Second, Third Fragment 연결해준 것과 마찬가지로 FirstFragment 안에 들어가는 ViewPager를 연결

		fragment_viewpager.adapter = fragmentViewpagerAdapter
	
        	sample_tab.setupWithViewPager(fragment_viewpager)
        	sample_tab.apply {
            		getTabAt(0)?.text = "INFO"
            		getTabAt(1)?.text = "OTHER"
	    
* Info, OtherFragment 간의 전환을 위해 TabLayout을 함께 구현
* FirstFragment의 xml layout에 fragment_viewpager 추가 -> fragment의 id.adapter 연결
* !!! 인자 잘 확인하기 !!!

> InfoFragmnet

	return inflater.inflate(R.layout.fragment_info, container, false)
	
* Fragment는 inflater 사용해서 xml과 연동시켜주기
* OtherFragment도 마찬가지
* Inflater란 ? xml로 정의된 view(or menu etc...)를 실제 객체화 시켜주는 것
