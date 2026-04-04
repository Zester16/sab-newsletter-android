package com.oschmid.sabnewsletter.views.bloomberg

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.oschmid.sabnewsletter.database.NewsLetterImageMapper
import com.oschmid.sabnewsletter.domain.SabencosNewsletersDomain
import com.oschmid.sabnewsletter.repository.AuthenticationRepository
import com.oschmid.sabnewsletter.repository.CheckRepository
import com.oschmid.sabnewsletter.repository.SabencosNewsletterRepository
import com.oschmid.sabnewsletter.views.newslist.NewsLetterList


@Composable
fun BloombergView(
    context: Context, navController: NavHostController, viewModel: BloombergViewModel = viewModel(
        factory = BloombergViewModelFactory(
            checkRepository = CheckRepository(context, navController),
            newsletterRepository = SabencosNewsletterRepository(
                context, navController = navController
            )
        )
    )
) {
    //val context= LocalContext.current

    val authenticationRepository = AuthenticationRepository(context, navController)
    val newsLetters by viewModel.newsletterList.observeAsState(emptyList<SabencosNewsletersDomain>())
    Column {
        Text(text = "This is Dashboard")

        Button(onClick = { viewModel.getNewsLetters() }) {
            Text("Refresh")
        }
        if (!newsLetters.isNullOrEmpty()) {
            NewsLetterList(
                newsletersDomain = newsLetters!!,
                navController,
                newsImage = NewsLetterImageMapper.BLOOMBERG_LOGO
            )
        }

    }


}