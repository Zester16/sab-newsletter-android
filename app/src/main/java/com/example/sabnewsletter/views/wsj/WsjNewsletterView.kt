package com.example.sabnewsletter.views.wsj

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import com.example.sabnewsletter.domain.SabencosNewsletersDomain
import com.example.sabnewsletter.repository.SabencosNewsletterRepository
import com.example.sabnewsletter.views.newslist.NewsLetterList

@Composable
fun WsjNewsletterView(context: Context, navController: NavHostController) {
    val newsletterRepository=SabencosNewsletterRepository(context,navController)
    val viewModel = WsjNewsletterViewmodel(newsletterRepository = newsletterRepository)

    val newsLettersList by viewModel.newsletterList.observeAsState(emptyList<SabencosNewsletersDomain>())
//    Button(onClick ={viewModel.} ){
//        Text("Refresh")
//    }
    if(!newsLettersList.isNullOrEmpty()){
        NewsLetterList(newsletersDomain = newsLettersList!!,navController)
    }
}