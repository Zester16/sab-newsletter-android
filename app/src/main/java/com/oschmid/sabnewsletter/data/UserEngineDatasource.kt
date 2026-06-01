package com.oschmid.sabnewsletter.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

//this request body is used for sending user read details
@Serializable
data class UserReadDatasource( @SerializedName("newsId")val newsId:String,@SerializedName("newsletterId")val newsletterId:String,@SerializedName("status")val status:Int,@SerializedName("timeRead") var readTime:Int,@SerializedName("publishedDate") val date:String)

//this response body is used for getting news read for a particular news source
@Serializable
data class UserNewsReadDatasource( @SerializedName("newsId")val newsId:String,@SerializedName("newsletterId")val newsletterId:String,@SerializedName("status")val status:Int,@SerializedName("timeRead") val readTime:Int, @SerializedName("udatedAt") val updatedAt:String)

@Serializable
data class UserNewsReadResponseDatasource(val status:Int,val data:List<UserNewsReadDatasource>?)

//this class is used for reading response
data class UserReadResponse(val statusCode:Int?,val statusMessage:String?);