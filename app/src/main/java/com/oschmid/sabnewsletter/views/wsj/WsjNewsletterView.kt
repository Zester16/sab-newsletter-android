package com.oschmid.sabnewsletter.views.wsj

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.oschmid.sabnewsletter.database.NewsLetterImageMapper
import com.oschmid.sabnewsletter.views.wsj.WsjNewsletterVMFactory
import com.oschmid.sabnewsletter.views.wsj.WsjNewsletterViewmodel
import com.oschmid.sabnewsletter.domain.SabencosNewsletersDomain
import com.oschmid.sabnewsletter.repository.SabencosNewsletterRepository
import com.oschmid.sabnewsletter.views.newslist.NewsLetterList

@Composable
fun WsjNewsletterView(
    context: Context, navController: NavHostController,
    viewModel: WsjNewsletterViewmodel = viewModel(
        factory = WsjNewsletterVMFactory(
            newsletterRepository = SabencosNewsletterRepository(
                context = context,
                navController = navController
            )
        )
    )
) {
//    val newsletterRepository=SabencosNewsletterRepository(context,navController)
//    val viewModel = WsjNewsletterViewmodel(newsletterRepository = newsletterRepository)

    val newsLettersList by viewModel.newsletterList.observeAsState(emptyList<SabencosNewsletersDomain>())

    if (!newsLettersList.isNullOrEmpty()) {
        NewsLetterList(
            newsletersDomain = newsLettersList!!,
            navController,
            newsImage = NewsLetterImageMapper.WSJLOGO
        )
    } else {
        //    Button(onClick ={viewModel.} ){
//        Text("Refresh")
//    }
    }
}