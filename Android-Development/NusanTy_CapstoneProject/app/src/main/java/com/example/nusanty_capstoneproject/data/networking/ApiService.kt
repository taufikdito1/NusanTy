package com.example.nusanty_capstoneproject.data.networking

import com.example.nusanty_capstoneproject.BuildConfig.API_KEY
import com.example.nusanty_capstoneproject.data.model.article.DetailResponse
import com.example.nusanty_capstoneproject.data.model.login.LoginResponse
import com.example.nusanty_capstoneproject.data.model.login.RegisterResponse
import retrofit2.Call
import retrofit2.http.*

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

    @Headers("api-key: $API_KEY")
    @GET("article-api/article")
    fun getArticle() : Call<DetailResponse>
}