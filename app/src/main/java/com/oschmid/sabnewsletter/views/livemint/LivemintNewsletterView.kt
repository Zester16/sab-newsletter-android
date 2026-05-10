package com.oschmid.sabnewsletter.views.livemint

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.oschmid.sabnewsletter.domain.SabencosNewsletterImagelessDomain
import com.oschmid.sabnewsletter.navigation.WebviewNewsReadKeyConstant
import com.oschmid.sabnewsletter.repository.CheckRepository
import com.oschmid.sabnewsletter.repository.SabencosNewsletterRepository
import com.oschmid.sabnewsletter.ui.theme.SabencosBlue
import com.oschmid.sabnewsletter.ui.theme.SabencosYellow
import com.oschmid.sabnewsletter.views.newslist.NewsLetterImagelessGridView

/**
 *  This is main view of Livemint top of the morning newsletter
 * **/
@Composable
fun LivemintNewsletterView(
    context: Context,
    navController: NavHostController,
    viewmodel: LivemintnewsletterViewModel = viewModel(
        factory = LivemintNewsletterViewmodelFactory(
            checkRepository = CheckRepository(
                context, navController
            ), newsletterRepository = SabencosNewsletterRepository(
                context, navController = navController
            )
        )
    )
) {

    val newsList by viewmodel.newsletterList.observeAsState(emptyList<SabencosNewsletterImagelessDomain>())
    val isLoading by viewmodel.isLoading.observeAsState(false)
    if (navController.currentBackStackEntry!!.savedStateHandle.contains(WebviewNewsReadKeyConstant.NEWSREAD_TIME_KEY)) {
        val readTime =
            navController.currentBackStackEntry!!.savedStateHandle.get<Long>(
                WebviewNewsReadKeyConstant.NEWSREAD_TIME_KEY
            ) ?: 0L
        Toast.makeText(LocalContext.current, readTime.toString(), Toast.LENGTH_SHORT).show()
        newsList?.forEach { news ->
            if (news?.id == "7-January-2026") {
                news.read = true
            }
        }
        navController.currentBackStackEntry
            ?.savedStateHandle
            ?.remove<Long>(WebviewNewsReadKeyConstant.NEWSREAD_TIME_KEY)

    }

    Row(
        modifier = Modifier
            .fillMaxHeight()
            .background(SabencosYellow),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(64.dp)
                    .background(SabencosYellow),
                color = SabencosBlue,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        } else {

            if (!newsList.isNullOrEmpty()) {
                NewsLetterImagelessGridView(
                    newsletterList = newsList!!,
                    navController = navController
                )
                Text("Oops!! Some Error Happend")
                Button(onClick = { viewmodel.getNewsletter() }) {
                    Text("Click here to reload")
                }
            }
        }
    }


}