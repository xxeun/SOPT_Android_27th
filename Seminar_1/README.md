# SOPT_Android_27th

<div>
	<img src="https://user-images.githubusercontent.com/46614405/96208593-e2dd2c00-0fa8-11eb-8aa7-04dad2ac31c1.gif">
	<img src="https://user-images.githubusercontent.com/46614405/96208604-e5d81c80-0fa8-11eb-8995-c878dc8f8e09.gif">
</div>

> 과제 완료 2020.10.11
> 리드미 작성 2020.10.16
>> 기능은 함수로 구현하는 습관!
-------------------------

 ### 1. **기본과제**

> SignUpActivity 만들기
 

    fun gotoSignUp() {  
    signUp.setOnClickListener {  
    val intent = Intent(this, SignUpActivity::class.java)  
    startActivityForResult(intent,REQUEST_CODE_SIGNUP)
	    }  
	 }

 * gotoSignUp 함수 : SignUpActivity로 화면전환
 
 > SignUpActivity 내의 SignUpProcess() 함수
 
 	sign_Up.setOnClickListener {  
       //필수사항 입력칸 공백 체크  
       if (u_name.text.toString().equals("") || u_id.text.toString().equals("") || u_pw.text.toString().equals("") || u_pw_c.text.toString().equals("")  
        )  
                Toast.makeText(this, "빈칸을 입력해주세요!", Toast.LENGTH_SHORT).show()  
            //비밀번호 일치 여부 확인  
            else if(!u_pw.text.toString().equals(u_pw_c.text.toString()))  
                Toast.makeText(this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show()  
            else {  
                Toast.makeText(this, "회원가입 완료!", Toast.LENGTH_SHORT).show()  
		
 * equals 함수와 || 연산자 이용하여 필수 입력 칸의 공백 확인
 * 빈칸이 1개라도 있는 경우 Toast.makeText 통해서 빈칸 입력 메시지 출력
 
 
----------------------
 
 ### 2. **성장과제1**
 
 StartActivityForResult() 활용해서 
 1. 회원가입 성공하면 이전 로그인 화면으로 돌아오기
 2. 입력한 아이디와 비밀번호 보이기
 
> SignUpActivity
                
		intent.putExtra("id", u_id.text.toString())
                intent.putExtra("pw", u_pw.text.toString())
                startActivityForResult(intent, REQUEST_CODE_LOGIN)
putExtra, startActivityForResult 활용

> MainActivity

	fun ShowData() {
        	if(intent.hasExtra("id")){
            	Id.setText(intent.getStringExtra("id"))
       	 }	
        	if(intent.hasExtra("pw")){
            	password.setText(intent.getStringExtra("pw"))
        	}
    }
* MainActivity에서 hasExtra 사용해서 id, pw 받아온 후 setText로 SignUpActivity에서 입력한 아이디, 비밀번호 저장.
* REQUEST_CODE_LOGIN 변수 만들어서 활용

----------------------


### 3. **성장과제2**

> MainActivity에서 Login, autoLogin 함수 구현

 	lateinit var pref:SharedPreferences
    	lateinit var editor:SharedPreferences.Editor
	
> SharedPreferences 사용하기 위해서는 pref:editor 활용해야함

	fun autoLogIn() {
        //자동로그인 체크가 false인게 true거나, Id에 Null or Blank가 아니면,
        	if(pref.getBoolean("checkAutoLogin",false) == true || !pref.getString("Id",null).isNullOrBlank()){
            		Toast.makeText(this,"자동로그인 되었습니다!",Toast.LENGTH_SHORT).show()
            		val intent = Intent(applicationContext, HomeActivity::class.java)
            		startActivityForResult(intent,REQUEST_CODE_LOGIN)
            		/*val intent = Intent(this, HomeActivity::class.java)
                			startActivity(intent)*/
        		}
  	  }
   
   * getBoolean, getString 함수 사용하여 자동로그인이 false인 경우, 혹은 id에 null값이 아닌 경우에 자동 로그인 구현되도록 함.
   * 자동로그인 성공하면 자동로그인 성공 메시지와 함께 HomeActivity로 화면전환
   
