package com.oschmid.sabnewsletter.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

//this request body is used for sending user read details
data class UserReadDatasource( @SerializedName("newsId")val newsId:String,@SerializedName("newsletterId")val newsletterId:String,@SerializedName("status")val status:Int,@SerializedName("readTime")val readTime:Int)

//this class is used for reading response
data class UserReadResponse(val statusCode:Int?,val statusMessage:String?);