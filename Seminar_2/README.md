# SOPT_Android_27th_Seminar2

<div>
	<img src="https://user-images.githubusercontent.com/46614405/97428763-cdc5ad00-1959-11eb-8eb9-00ecbc30a46e.gif", height=500>
	<img src="https://user-images.githubusercontent.com/46614405/97428955-15e4cf80-195a-11eb-8e1e-903c24d34433.jpg", height=500>
</div>

> 2차 세미나 필수과제, 성장과제1
>> 과제 완료일 2020.10.26, 리드미 작성일 2020.10.28
------------

## RecyclerView ##
List 구현을 위해 사용하는 View. 쉽게 말해, User가 관리하는 모든 Data를 item 단위로 구성하여 화면에 출력하는 View라고 이해하면 됨.
## ViewHolder ##
화면에 표시될 아이템 뷰를 저장하는 객체
## Adapter ##
데이터 목록을 아이템 단위의 뷰로 구성하여 화면에 표시해주는 역할

------------
# 필수과제 #
> RecyclerViewActivity

    sampleAdapter = SampleAdapter(this)

        view_rcv.adapter = sampleAdapter
        view_rcv.layoutManager = LinearLayoutManager(this)

        sampleAdapter.data = mutableListOf(
            SampleData("1","소개", "2020.10.18","남가은, 22살, 숙명여자대학교 소비자경제학과, IT공학전공"),
            SampleData("2","관심 분야","2020.10.18","IT, 소비자학, 시장분석"),
            SampleData("3","깃허브","2020.10.18","안드로이드 초보의 깃허브 : https://github.com/xxeun")
        )
        sampleAdapter.notifyDataSetChanged()
       
* Adapter를 활용해서 data를 List로 출력해주는 View
* notifyDataSetChanged() 함수 사용해서 DataSet가 바뀌었음을 알려줌
-----------
> SampleAdapterActivity

    var data = mutableListOf<SampleData>()
    
* DataSet을 mutableListOF로 받아옴

        holder.itemView.setOnClickListener(){
            //SampleData.xml의 리스트가 들어있는 data 변수를 get함수와 함께 사용하여 sampleData 변수에 넣어줌
            val sampleData = data.get(position)

            var gTitle : String = sampleData.title
            var gSubtitle : String = sampleData.subTitle
            var gWriteday : String = sampleData.writeDay
            var gInfo : String = sampleData.info

            //Intent 활용하여 ShowDetailActivity에게 데이터 넘겨주는 과정
            val intent = Intent(context, ShowDetailActivity::class.java)

            //putExtra 이용해서 sampleData의 각 변수를 String으로 저장했던 gTitle~gInfo와 key값을 넘겨줌
            intent.putExtra("iTitle", gTitle)
            intent.putExtra("iSubTitle",gSubtitle)
            intent.putExtra("iWriteDay",gWriteday)
            intent.putExtra("iInfo",gInfo)

            context.startActivity(intent)
        }

* intent : Activity 간 데이터 전달을 위해 생성한 객체로, 위 코드에서는 RecyclerView의 item을 클릭하면 상세정보를 보여주는 ShowDetailActivity로 전환
* putExtra() : intent로 값을 주고받기 위해 호출. Key값과 value값을 넣어줌
-----------
> ShowDetailActivity
    
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
        
* RecyclerViewActivity에 있는 아이템 클릭 시 보여지는 상세정보 액티비티
* Adapter에서 putExtra()함수를 호출해서 넘긴 데이터를 getStringExtra() 함수를 사용해서 받아옴. putExtra() 호출할 때 사용했던 key값 이용
* setText()함수 사용해서 받아온 데이터 텍스트로 지정
-----------
# 성장과제1 #
> RecyclerViewActivity에서 구현

    view_rcv.layoutManager = GridLayoutManager(this, 3, RecyclerView.VERTICAL, false)
    
* GridLayoutManager 활용. 인자 중 '3'은 그리드로 정렬 시 한 줄에 보여지는 item의 개수를 지정한 것.
