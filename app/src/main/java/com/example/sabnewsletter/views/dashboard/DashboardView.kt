package com.example.sabnewsletter.views.dashboard

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.sabnewsletter.repository.AuthenticationRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sabnewsletter.domain.SabencosNewsletersDomain
import com.example.sabnewsletter.repository.CheckRepository
import com.example.sabnewsletter.repository.SabencosNewsletterRepository
import com.example.sabnewsletter.views.newslist.NewsLetterList


@Composable
fun DashboardView(context: Context, navController: NavHostController, viewModel: DashboardViewModel = viewModel(
    factory = DashboardViewModelFactory(checkRepository = CheckRepository(LocalContext.current,navController), newsletterRepository=SabencosNewsletterRepository(
        LocalContext.current, navController = navController))
)
) {
    //val context= LocalContext.current

    val authenticationRepository=AuthenticationRepository(context,navController)
    val newsLetters by viewModel.newsletterList.observeAsState(emptyList<SabencosNewsletersDomain>())
    Column {
        Text(text = "This is Dashboard")

        Button(onClick ={viewModel.getNewsLetters()} ){
            Text("Refresh")
        }
        if(!newsLetters.isNullOrEmpty()){
            NewsLetterList(newsletersDomain = newsLetters!!,navController)
        }

    }


}