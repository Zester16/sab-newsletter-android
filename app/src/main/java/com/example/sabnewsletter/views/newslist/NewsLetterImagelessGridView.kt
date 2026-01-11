package com.example.sabnewsletter.views.newslist

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sabnewsletter.R
import com.example.sabnewsletter.domain.SabencosNewsletterImagelessDomain
import com.example.sabnewsletter.ui.theme.SabencosBlue
import com.example.sabnewsletter.ui.theme.SabencosYellow
import com.example.sabnewsletter.ui.theme.fontUbuntu
import com.example.sabnewsletter.utils.invokeNavigationToInternalWebBrowser

@Composable
fun NewsLetterImagelessGridView (newsletterList: List<SabencosNewsletterImagelessDomain?>, navController: NavHostController){

    //TODO: add image to livemint newsletter

    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp),Modifier.background(color = SabencosYellow)) {

        item{val mintBanner = painterResource(id = R.drawable.ming_top_of_morning)
            Image(painter = mintBanner, contentDescription = "sabencos logo", modifier = Modifier
                , alignment = Alignment.Center)
        }

        items(newsletterList){ news->
            if(news != null){
                NewsletterJustDateView(news, navController = navController)
            }

        }
    }
}

@Composable
    fun NewsletterJustDateView(news:SabencosNewsletterImagelessDomain,navController: NavHostController){
        Card(onClick = {
            Log.v("NewsLetter On CLick",news.url)
            invokeNavigationToInternalWebBrowser(news.url,navController)
        }, modifier = Modifier.padding(12.dp).fillMaxWidth(),elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )) {
            news.date?.let { DateView(it) }
            if (navController.currentBackStackEntry!!.savedStateHandle.contains("read-time")) {
                val readTime =
                    navController.currentBackStackEntry!!.savedStateHandle.get<Long>(
                        "read-time"
                    ) ?: 0L
                Toast.makeText(LocalContext.current,readTime.toString(),Toast.LENGTH_SHORT).show()

                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.remove<Long>("read-time")

            }
//            news.date?.let {
//                val splitDate=it.split("-")
//                Text(splitDate[0], textAlign = TextAlign.Center, fontSize = 24.sp, color = SabencosYellow,)
//                Text(splitDate[1],textAlign = TextAlign.Center, fontSize = 24.sp, color = Color.White,)
//                Text(splitDate[2],textAlign = TextAlign.Center, fontSize = 24.sp, color = Color.White,)
//            }
        }

    }

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun DateView(date:String){
    val splitDate=date.split("-")
    val month = splitDate[1].uppercase().take(3)
    Column(modifier = Modifier.background(color = SabencosBlue).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(splitDate[0], textAlign = TextAlign.Center, fontSize = 40.sp, color = SabencosYellow, fontFamily = fontUbuntu)
        Spacer(Modifier.height(12.dp))
        Text(month,textAlign = TextAlign.Center, fontSize = 28.sp, color = Color.White,fontFamily = fontUbuntu)
        Spacer(Modifier.height(12.dp))
        Text(splitDate[2],textAlign = TextAlign.Center, fontSize = 24.sp, color = Color.White,)
        Spacer(Modifier.height(12.dp))
    }

}