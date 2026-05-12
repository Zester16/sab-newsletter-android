package com.oschmid.sabnewsletter.utils


import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.oschmid.sabnewsletter.data.UserReadDatasource
import com.oschmid.sabnewsletter.navigation.NavigationConstant
import java.net.URLDecoder
import java.net.URLEncoder


//for invoking navigation to browser, since it takes data from path param
fun invokeNavigationToInternalWebBrowser(
    url: String,
    navHostController: NavHostController,
    key: String?,
    newsId: String?
) {
    val encodedUrl = encode(url)
    val webviewPath=NavigationConstant.WEBVIEW.replace("{news_url}", encodedUrl)
    if(!key.isNullOrEmpty() && !newsId.isNullOrEmpty()){
        val userDataSource=UserReadDatasource(newsId=newsId, newsletterId = key, status = 2, readTime = 0)
        val encodedNewsBody = encode(Gson().toJson(userDataSource))
        navHostController.navigate(webviewPath.replace("{news_body}",encodedNewsBody))
    }else{
        navHostController.navigate(webviewPath.replace("&news_body={news_body}",""))
    }
    //navHostController.navigate(webviewPath)
}

fun encode(url: String) = URLEncoder.encode(url, "UTF-8")

fun decode(url: String) = URLDecoder.decode(url, "UTF-8")