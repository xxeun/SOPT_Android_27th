# SOPT_Android_27th

> 2차 세미나 필수과제, 성장과제1
>> 과제 완료일 2020.10.26, 리드미 작성일 2020.10.28
------------

## RecyclerView ##
List 구현을 위해 사용하는 View. 쉽게 말해, User가 관리하는 모든 Data를 item 단위로 구성하여 화면에 출력하는 View라고 이해하면 됨.
## ViewHolder ##
화면에 표시될 아이템 뷰를 저장하는 객체
## Adapter ##
데이터 목록을 아이템 단위의 뷰로 구성하여 화면에 표시해주는 역할

> RecyclerView

    sampleAdapter = SampleAdapter(this)

        view_rcv.adapter = sampleAdapter

        sampleAdapter.data = mutableListOf(
            SampleData("1","소개", "2020.10.18","남가은, 22살, 숙명여자대학교 소비자경제학과, IT공학전공"),
            SampleData("2","관심 분야","2020.10.18","IT, 소비자학, 시장분석"),
            SampleData("3","깃허브","2020.10.18","안드로이드 초보의 깃허브 : https://github.com/xxeun")
        )
        sampleAdapter.notifyDataSetChanged()
       

