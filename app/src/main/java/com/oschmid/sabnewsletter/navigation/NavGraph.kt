package com.oschmid.sabnewsletter.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.gson.Gson
import com.oschmid.sabnewsletter.data.UserReadDatasource
import com.oschmid.sabnewsletter.utils.decode
import com.oschmid.sabnewsletter.views.bloomberg.BloombergView
import com.oschmid.sabnewsletter.views.dashboard.DashboardView
import com.oschmid.sabnewsletter.views.livemint.LivemintNewsletterView
import com.oschmid.sabnewsletter.views.login.LoginView
import com.oschmid.sabnewsletter.views.splash.SplashView
import com.oschmid.sabnewsletter.views.webview.NewsWebviewWithJs
import com.oschmid.sabnewsletter.views.wsj.WsjNewsletterView


@Composable
fun NavGraph(context: Context, navController: NavHostController) {

    NavHost(navController = navController, startDestination = NavigationConstant.SPLASH) {
        composable(NavigationConstant.SPLASH) { it ->
            SplashView(navController)

        }
        composable(NavigationConstant.DASHOBARD) {
            //BloombergView(context,navController)
            DashboardView(context, navController)
        }
        composable(NavigationConstant.LIVEMINT) {
            LivemintNewsletterView(context, navController)
        }
        composable(NavigationConstant.WSJ) {
            WsjNewsletterView(context, navController)
        }
        composable(NavigationConstant.LOGIN) {
            LoginView(navController)
        }
        composable(NavigationConstant.WEBVIEW) { navBackStackEntry ->
            val newsUrl = navBackStackEntry.arguments?.getString("news_url")
            val newsBody = navBackStackEntry.arguments?.getString("news_body")
            if (newsUrl != null) {
                val decodedUrl = decode(newsUrl)
                var newsBodyDecoded: UserReadDatasource? =null;

                if(newsBody != null){
                 newsBodyDecoded=  Gson().fromJson(decode(newsBody), UserReadDatasource::class.java)
                }

                NewsWebviewWithJs(navController, decodedUrl,newsBodyDecoded)
            }

        }
        composable(NavigationConstant.BLOOMBERG) {
            BloombergView(context, navController)
        }

    }

}