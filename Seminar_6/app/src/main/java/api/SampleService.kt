package api

import data.LoginRequest
import data.LoginResponse
import data.SignUpRequest
import data.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

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