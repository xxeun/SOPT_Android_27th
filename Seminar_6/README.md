# SOPT_Android_27th_Seminar6

<div>
	<img src="https://user-images.githubusercontent.com/46614405/101271826-b7690780-37c9-11eb-8486-21c68128b1ff.gif", height=500>
	<img src="https://user-images.githubusercontent.com/46614405/101271828-bd5ee880-37c9-11eb-8845-45da067acd53.gif", height=500>
	<img src="https://user-images.githubusercontent.com/46614405/101271844-f6975880-37c9-11eb-899e-c89f2fc6b9e8.png", height=300>
	<img src="https://user-images.githubusercontent.com/46614405/101271849-fc8d3980-37c9-11eb-8be5-420a9cfc8d1a.png", height=300>
</div>

> 6차 세미나 필수과제
>> 과제 완료일 2020.11.29, 리드미 작성일 2020.12.06
------------

## 서버통신 ##
* Request(요청 객체)
* Response(응답 객체)
* PostMan 통신

------------

# 필수과제 #

> 회원가입 및 로그인 서버통신 구현

## Interface

	interface SampleService {
   		 @Headers("Content-Type:application/json")
   		 @POST("/users/signin")
    		fun postLogin(
       		@Body body : LoginRequest
    		) : Call<LoginResponse>
    		@POST("/users/signup")
    		fun postSignUp(
       		@Body body : SignUpRequest
    		) : Call<SignUpResponse>
	}

* 회원가입과 로그인 Request는 Body에 넣어줌

## SignUpRequest

	class SignUpRequest(
    		val email : String,
   	 	val password : String,
    		val userName : String
	)
	
* 서버 SignUp에서 요구하는 데이터 형식
	
## SignUpResponse

	data class SignUpResponse(
    		val `data`: Data,
    		val message: String,
    		val status: Int,
    		val success: Boolean
	) {
    	data class Data(
        	val email: String,
        	val userName: String,
        	val password: String
   	 	)
	}
	
## LoginRequest

	class LoginRequest(
    		val email : String,
    		val password : String
		)
		
* 서버 Login에서 요구하는 데이터 형식

## LoginResponse

	data class LoginResponse(
		val `data`: Data,
    		val message: String,
    		val status: Int,
    		val success: Boolean
	) {
    	data class Data(
        	val email: String,
        	val password: String,
        	val userName: String
    		)
	}
	
## MainActivity - 로그인 함수 구현 액티비티

	Login.setOnClickListener {
            val email = id.text.toString()
            val password = pw.text.toString()
            val call : Call<LoginResponse> = SampleServiceImpl.service.postLogin(
                LoginRequest(email = email, password = password)
            )
            call.enqueue(object : Callback<LoginResponse>{
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    // 통신 실패 로직
                }
		
* LoginResponse 객체를 Call을 이용하여 받아옴

		override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    response.takeIf { it.isSuccessful}
                        ?.body()
			
* Response를 받아온 후, 요청에 성공하면 Let문을 이용하여 Login 함수 로직 구현

## SignUpActivity

	sign_up.setOnClickListener{
            val call : Call<SignUpResponse> = SampleServiceImpl.service.postSignUp(
                SignUpRequest(
                    u_id.text.toString(),
                    u_pw.text.toString(),
                    u_name.text.toString()
                )
            )
	    
* SignUpRequest 데이터를 test.toString 함수 이용하여 String으로 변환
* Response 객체 받아온 후, 서버에 요청 성공하면 Let문 이용하여 SignUp 함수 로직 구현
