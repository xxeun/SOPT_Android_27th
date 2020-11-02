# SOPT_Android_27th_Seminar3

<div>
	<img src="https://user-images.githubusercontent.com/46614405/97841821-eb17c400-1d29-11eb-8021-c0f2969d40d7.gif", height=500>
	<img src="https://user-images.githubusercontent.com/46614405/97841847-f66aef80-1d29-11eb-9009-882e0f8bd141.gif", height=500>
	<img src="https://user-images.githubusercontent.com/46614405/97841885-071b6580-1d2a-11eb-9e8b-fc99036e23c5.gif", height=500>
</div>

> 3차 세미나 필수과제
>> 과제 완료일 2020.11.02, 리드미 작성일 2020.11.02
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
# 필수과제 #
> RecyclerViewActivity

