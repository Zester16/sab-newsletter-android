package com.example.sabnewsletter.network


import com.example.sabnewsletter.domain.SabencosNewsletersDomain
import com.example.sabnewsletter.domain.SabencosNewsletterImagelessDomain
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import java.util.concurrent.TimeUnit

val localUrl="http://10.0.2.2:3001/"
val sabUrl = "https://www.sabencos.co.in/"


    private val okHttp= OkHttpClient.Builder()
    .connectTimeout(60, TimeUnit.SECONDS)
    .callTimeout(60, TimeUnit.SECONDS)
    .readTimeout(120, TimeUnit.SECONDS)
    .writeTimeout(120, TimeUnit.SECONDS)
    .build()
val retrofitAuthentication = Retrofit.Builder()
    .baseUrl(sabUrl)
    .client(okHttp)
    .addConverterFactory(GsonConverterFactory
        .create())
    .build()

data class UserLoginRequest(@SerializedName("user_name") val username:String,  @SerializedName("password")val password:String)
data class UserLoginResponse(val auth:String,val ref:String)
data class UserTokenCheckResponse(val check:String)

//for getting type one of newsletters
data class SabencosNewsletters(val title:String,val date:String,@SerializedName("img_url")val imageUrl:String,val url:String)
fun List<SabencosNewsletters>.toNewsLetterDatasource(): List<SabencosNewsletersDomain> {
    return this.map { SabencosNewsletersDomain(date=it.date,imageUrl=it.imageUrl, url = it.url, title = it.title) }
}

//for getting type two of newsletter
data class SabencosNewslettersImageless(val date:String,@SerializedName("newsUrl")val url:String )
fun List<SabencosNewslettersImageless>.toNewsLetterImagelessDatasource():List<SabencosNewsletterImagelessDomain>{
    return this.map{
        SabencosNewsletterImagelessDomain(date = it.date, url = it.url)
    }
}
interface Authentication{
    @FormUrlEncoded
    @POST("auth/login")
    fun loginUser(@Field("username") username:String, @Field("password")password: String):Call<UserLoginResponse>

    @POST("auth/refresh")
    fun refreshToken(@HeaderMap headers: Map<String, String>):Call<UserLoginResponse>

    @GET("auth/test")
    fun testGet(@HeaderMap headers: Map<String, String>):Call<UserTokenCheckResponse>
}
interface SabencosNewslettersInterface{
    @GET("newsletter/all-news/bloomberg")
    fun getBloombergNewsletter(@HeaderMap headers: Map<String, String>):Call<List<SabencosNewsletters>>
    @GET("newsletter/all-news/mint-top-of-morning")
    fun getMintTopOfMorningNewsletters(@HeaderMap headers: Map<String, String>):Call<List<SabencosNewslettersImageless>>
    @GET("newsletter/all-news/wsj-newsletter")
    fun getWsjNewsletters(@HeaderMap headers: Map<String, String>):Call<List<SabencosNewsletters>>
}

object SabencosAuthentication {
    val authenticationApi:Authentication by lazy {
        retrofitAuthentication.create(Authentication::class.java)
    }
}

object SabencosNewslettersObject{
    val sabencosNewsletters:SabencosNewslettersInterface by lazy{
        retrofitAuthentication.create(SabencosNewslettersInterface::class.java)
    }
}
