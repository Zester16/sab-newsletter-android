package com.example.sabnewsletter.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable
import com.example.sabnewsletter.utils.decode
import com.example.sabnewsletter.views.dashboard.DashboardView
import com.example.sabnewsletter.views.livemint.LivemintNewsletterView
import com.example.sabnewsletter.views.login.LoginView
import com.example.sabnewsletter.views.splash.SplashView
import com.example.sabnewsletter.views.webview.NewsWebviewWithJs

@Composable
fun NavGraph(context: Context, navController: NavHostController) {

    NavHost(navController = navController, startDestination = NavigationConstant.SPLASH){
        composable(NavigationConstant.SPLASH){ it->
            SplashView(navController)

        }
        composable(NavigationConstant.DASHOBARD){
            DashboardView(context,navController)
        }
        composable(NavigationConstant.LIVEMINT){
            LivemintNewsletterView(context,navController)
        }
        composable(NavigationConstant.LOGIN){
            LoginView(navController)
        }
        composable(NavigationConstant.WEBVIEW){navBackStackEntry ->
            val newsUrl = navBackStackEntry.arguments?.getString("news_url")
            if (newsUrl != null) {
                val decodedUrl = decode(newsUrl)
                NewsWebviewWithJs(navController,decodedUrl)
            }

        }

    }

}