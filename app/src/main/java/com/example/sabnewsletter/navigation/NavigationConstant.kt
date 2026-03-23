package com.example.sabnewsletter.navigation

//path constants for navigation
object NavigationConstant {
const val LOGIN="/login"
 const val SPLASH="/splash"
 const val DASHOBARD="/dashboard"
 const val LIVEMINT="/livemint"
 const val WSJ = "/wsj"
 const val BLOOMBERG="/bloomberg"
 const val WEBVIEW="/webview?news_url={news_url}"

}

//extra variable name for web view navigation
//used for adding, retriving, deleting extra variable between composables

object WebviewNewsReadKeyConstant {

 const val NEWSREAD_TIME_KEY="news-read"
 const val NEWS_ID="news-letter-id"
 const val NEWS_KEY="news-key"

}