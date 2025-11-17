package com.example.sabnewsletter.views.wsj

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sabnewsletter.database.NewsLetterImageMapper
import com.example.sabnewsletter.domain.SabencosNewsletersDomain
import com.example.sabnewsletter.repository.SabencosNewsletterRepository
import com.example.sabnewsletter.views.newslist.NewsLetterList

@Composable
fun WsjNewsletterView(context: Context, navController: NavHostController,
                      viewModel: WsjNewsletterViewmodel= viewModel(factory = WsjNewsletterVMFactory(newsletterRepository = SabencosNewsletterRepository(context = context, navController = navController)))
) {
//    val newsletterRepository=SabencosNewsletterRepository(context,navController)
//    val viewModel = WsjNewsletterViewmodel(newsletterRepository = newsletterRepository)

    val newsLettersList by viewModel.newsletterList.observeAsState(emptyList<SabencosNewsletersDomain>())

    if(!newsLettersList.isNullOrEmpty()){
        NewsLetterList(newsletersDomain = newsLettersList!!,navController, newsImage = NewsLetterImageMapper.WSJLOGO)
    }else{
        //    Button(onClick ={viewModel.} ){
//        Text("Refresh")
//    }
    }
}