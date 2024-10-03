package com.example.sabnewsletter.utils

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.sabnewsletter.navigation.NavigationConstant
import java.net.URLDecoder
import java.net.URLEncoder


//for invoking navigation to browser, since it takes data from path param
fun invokeNavigationToInternalWebBrowser(url:String, navHostController: NavHostController){
    val encodedUrl = encode(url)
    navHostController.navigate(NavigationConstant.WEBVIEW.replace("{news_url}",encodedUrl))
}

fun encode(url: String) = URLEncoder.encode(url, "UTF-8")

fun decode(url: String) = URLDecoder.decode(url, "UTF-8")