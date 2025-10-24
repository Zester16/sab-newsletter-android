package com.example.sabnewsletter.views.newslist

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.sabnewsletter.domain.SabencosNewsletersDomain
import com.example.sabnewsletter.utils.invokeNavigationToInternalWebBrowser


@Composable
fun NewsLetterList(newsletersDomain: List<SabencosNewsletersDomain?>,navController: NavHostController){

    LazyColumn() {
        items(newsletersDomain) { newsletter ->
            //newsletersDomain[message]?.let { NewsLetterIndividual(it) }
            if (newsletter != null) {
                NewsLetterIndividual(news = newsletter, navController = navController)
            }
        }

    }

}

@Composable
fun NewsLetterIndividual(news:SabencosNewsletersDomain?,navController: NavHostController){

    Card(onClick = {
        if (news != null) {
            Log.v("NewsLetter On CLick",news.url)
            invokeNavigationToInternalWebBrowser(news.url,navController)
        }
    }) {
        //Image(painter = , contentDescription = )

        AsyncImage(
            model = news?.imageUrl,
            contentDescription = null,
        )
        news?.date?.let { Text(it) }

    }
}