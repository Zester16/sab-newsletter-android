package com.example.sabnewsletter.views.webview

import android.os.Build
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import kotlin.time.*
import androidx.activity.compose.BackHandler

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsWebviewWithJs( navController: NavController,url:String){
    val context = LocalContext.current
    val stopwatch = TimeSource.Monotonic
    val startTime = stopwatch.markNow()
    Log.v("Webview",url)

    val webView = remember { WebView(context).apply {
        webViewClient = WebViewClient()
        settings.javaScriptEnabled = true
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        settings.safeBrowsingEnabled = true
        settings.allowContentAccess = true;
        settings.domStorageEnabled = true;
        this.settings.userAgentString="Mozilla/5.0 (Linux; Android 8.0; Pixel 2 Build/OPD3.170816.012) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Mobile Safari/537.36"
    }
    }

    AndroidView(
        modifier = Modifier
            .fillMaxSize(),
        factory = { webView },
        update = {
            it.loadUrl(url)
        })

    BackHandler(enabled = true) {
        val stopTime = stopwatch.markNow()
        val readTime = stopTime - startTime
//        navController.previousBackStackEntry
//            ?.savedStateHandle
//            ?.set("read-time", readTime.inWholeSeconds)
        navController.popBackStack()
    }
}
