package com.example.nusanty_capstoneproject.data.networking

import com.example.nusanty_capstoneproject.BuildConfig.API_KEY
import com.example.nusanty_capstoneproject.data.model.login.LoginResponse
import com.example.nusanty_capstoneproject.data.model.login.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @Headers("api-key: $API_KEY")
    @POST("auth-api/register")
    fun registerUser(
        @Field("name") name: String,
        @Field ("email") email: String,
        @Field ("password") password: String
    ) : Call<RegisterResponse>

    @FormUrlEncoded
    @Headers("api-key: $API_KEY")
    @POST("auth-api/login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password")password: String
    ): Call<LoginResponse>
}