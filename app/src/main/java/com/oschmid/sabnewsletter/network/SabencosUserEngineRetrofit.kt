package com.oschmid.sabnewsletter.network

import com.oschmid.sabnewsletter.data.UserReadDatasource
import com.oschmid.sabnewsletter.data.UserReadResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import java.util.concurrent.TimeUnit


// to connect to user newsletter engine
private val okHttp = OkHttpClient.Builder()
    .connectTimeout(60, TimeUnit.SECONDS)
    .callTimeout(60, TimeUnit.SECONDS)
    .readTimeout(120, TimeUnit.SECONDS)
    .writeTimeout(120, TimeUnit.SECONDS)
    .build()

 val userEngineRetrofit = Retrofit.Builder()
    .baseUrl("https://nf--sabencos-user-engine--8gcznmvxtxqt.code.run")
    .client(okHttp)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

// interface for sending information
interface SabencosUserEngineInterface{
    @POST("/v1/newsread")
    fun sendUserRead(@HeaderMap headers: Map<String, String>,@Body userReadDatasource: UserReadDatasource): Call<UserReadResponse>;

}

object SabencosUserEngineRetrofitObject{
    val userEngineRetrofitObject:SabencosUserEngineInterface by lazy {
        userEngineRetrofit.create(SabencosUserEngineInterface::class.java)
    }
}