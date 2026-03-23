package com.example.sabnewsletter.domain

import kotlinx.serialization.Serializable

//for domain having image and title
data class SabencosNewsletersDomain(val date:String,val url:String,val imageUrl:String,val title:String,val id:String?,val key:String?)
data class SabencosNewsletterImagelessDomain(val date:String, val url:String, val key:String?, val id:String?,
                                             var read:Boolean=false)
@Serializable
data class SabencosReadDomain(val id:String?,val duration:Long)