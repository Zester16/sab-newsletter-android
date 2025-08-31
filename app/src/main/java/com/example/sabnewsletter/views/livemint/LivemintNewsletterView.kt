package com.example.sabnewsletter.views.livemint

import android.content.Context
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.sabnewsletter.domain.SabencosNewsletterImagelessDomain
import com.example.sabnewsletter.network.SabencosNewslettersImageless
import com.example.sabnewsletter.views.newslist.NewsLetterImagelessGridView

/**
 *  This is main view of Livemint top of the morning newsletter
 * **/
@Composable
fun LivemintNewsletterView(context:Context,navController: NavHostController,) {

    val newsList = mutableListOf <SabencosNewsletterImagelessDomain>(
        SabencosNewsletterImagelessDomain(date = "2025-08-29", url = "https://www.livemint.com/mint-top-newsletter/minttopofthemorning29082025.html"),
        SabencosNewsletterImagelessDomain(date = "2025-08-28", url = "https://www.livemint.com/mint-top-newsletter/minttopofthemorning28082025.html"),
        SabencosNewsletterImagelessDomain(date = "2025-08-27", url = "https://www.livemint.com/mint-top-newsletter/minttopofthemorning27082025.html"),
        SabencosNewsletterImagelessDomain(date = "2025-08-26", url = "https://www.livemint.com/mint-top-newsletter/minttopofthemorning26082025.html"),
        SabencosNewsletterImagelessDomain(date = "2025-08-27", url = "https://www.livemint.com/mint-top-newsletter/minttopofthemorning25082025.html")
    )

    NewsLetterImagelessGridView(newsletterList = newsList, navController = navController)
}