package com.example.sabnewsletter.views.livemint

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sabnewsletter.domain.SabencosNewsletterImagelessDomain
import com.example.sabnewsletter.repository.CheckRepository
import com.example.sabnewsletter.repository.SabencosNewsletterRepository
import com.example.sabnewsletter.ui.theme.SabencosBlue
import com.example.sabnewsletter.ui.theme.SabencosYellow
import com.example.sabnewsletter.views.newslist.NewsLetterImagelessGridView

/**
 *  This is main view of Livemint top of the morning newsletter
 * **/
@Composable
fun LivemintNewsletterView(context:Context,navController: NavHostController,viewmodel:LivemintnewsletterViewModel= viewModel( factory = LivemintNewsletterViewmodelFactory(checkRepository = CheckRepository(
    context,navController), newsletterRepository= SabencosNewsletterRepository(
    context, navController = navController)
))
) {

    val newsList by viewmodel.newsletterList.observeAsState(emptyList<SabencosNewsletterImagelessDomain>())
    val isLoading by viewmodel.isLoading.observeAsState(false)
    //val newsList =
//        mutableListOf <SabencosNewsletterImagelessDomain>(
//        SabencosNewsletterImagelessDomain(date = "29-August-2025", url = "https://www.livemint.com/mint-top-newsletter/minttopofthemorning29082025.html"),
//        SabencosNewsletterImagelessDomain(date = "28-August-2025", url = "https://www.livemint.com/mint-top-newsletter/minttopofthemorning28082025.html"),
//        SabencosNewsletterImagelessDomain(date = "27-August-2025", url = "https://www.livemint.com/mint-top-newsletter/minttopofthemorning27082025.html"),
//        SabencosNewsletterImagelessDomain(date = "26-August-2025", url = "https://www.livemint.com/mint-top-newsletter/minttopofthemorning26082025.html"),
//        SabencosNewsletterImagelessDomain(date = "25-August-2025", url = "https://www.livemint.com/mint-top-newsletter/minttopofthemorning25082025.html")
//    )
    Row(modifier = Modifier.fillMaxHeight().background(SabencosYellow), verticalAlignment  = Alignment.Top, horizontalArrangement  = Arrangement.Center) {
        if(isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp).background(SabencosYellow),
                color = SabencosBlue,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
        else{

            if(!newsList.isNullOrEmpty()){
                NewsLetterImagelessGridView(newsletterList = newsList!!, navController = navController)
            }else{
                Text("Oops!! Some Error Happend")
                Button(onClick = {viewmodel.getNewsletter()},) {
                    Text("Click here to reload")
                }
            }
        }
    }



}