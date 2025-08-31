package com.example.sabnewsletter.views.newslist

import android.util.Log
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sabnewsletter.domain.SabencosNewsletterImagelessDomain
import com.example.sabnewsletter.utils.invokeNavigationToInternalWebBrowser

@Composable
fun NewsLetterImagelessGridView (newsletterList:List<SabencosNewsletterImagelessDomain>, navController: NavHostController){

    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
        items(newsletterList){ news->
            NewsletterJustDateView(news, navController = navController)
        }
    }
}

@Composable
    fun NewsletterJustDateView(news:SabencosNewsletterImagelessDomain,navController: NavHostController){
        Card(onClick = {
            if (news != null) {
                Log.v("NewsLetter On CLick",news.url)
                invokeNavigationToInternalWebBrowser(news.url,navController)
            }
        }) {
            news.date?.let { Text(it) }
        }

    }