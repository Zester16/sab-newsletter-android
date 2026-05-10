package com.oschmid.sabnewsletter.views.bloomberg

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.oschmid.sabnewsletter.database.NewsLetterImageMapper
import com.oschmid.sabnewsletter.domain.SabencosNewsletersDomain
import com.oschmid.sabnewsletter.repository.AuthenticationRepository
import com.oschmid.sabnewsletter.repository.CheckRepository
import com.oschmid.sabnewsletter.repository.SabencosNewsletterRepository
import com.oschmid.sabnewsletter.views.loader.CircularProgressComposable
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
    val isLoading by viewModel.isLoading.observeAsState(initial = true)
    if(isLoading){
        CircularProgressComposable()
    }
    else{
        if (!newsLetters.isNullOrEmpty()) {
            NewsLetterList(
                newsletersDomain = newsLetters!!,
                navController,
                newsImage = NewsLetterImageMapper.BLOOMBERG_LOGO
            )
        }else{

            Row( modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically){
                Text(text = "Some Error Happened")

                Button(onClick = { viewModel.getNewsLetters() }) {
                    Text("Click Here to reload")
                }


            }

        }
    }



}


