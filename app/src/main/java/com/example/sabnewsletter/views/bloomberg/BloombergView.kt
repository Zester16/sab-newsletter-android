package com.example.sabnewsletter.views.bloomberg

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.sabnewsletter.repository.AuthenticationRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sabnewsletter.domain.SabencosNewsletersDomain
import com.example.sabnewsletter.repository.CheckRepository
import androidx.navigation.NavHostController
import com.example.sabnewsletter.repository.SabencosNewsletterRepository
import com.example.sabnewsletter.views.newslist.NewsLetterList


@Composable
fun BloombergView(context: Context, navController: NavHostController, viewModel: BloombergViewModel = viewModel(
    factory = BloombergViewModelFactory(checkRepository = CheckRepository(context,navController), newsletterRepository=SabencosNewsletterRepository(
        context, navController = navController))
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