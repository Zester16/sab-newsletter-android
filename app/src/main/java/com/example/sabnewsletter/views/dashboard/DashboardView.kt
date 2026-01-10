package com.example.sabnewsletter.views.dashboard

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import com.example.sabnewsletter.R
import com.example.sabnewsletter.domain.SabencosNewsLetterDashCountDomain

import com.example.sabnewsletter.repository.SabencosNewsletterRepository
import com.example.sabnewsletter.ui.theme.SabencosYellow



@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun DashboardView(context: Context,navController: NavController,dashboardViewModel:DashboardViewModel= viewModel(factory =
    DashboardViewmodelFactory(SabencosNewsletterRepository(context = context,navController)) )){
    //val dashboardViewModel=DashboardViewModel(SabencosNewsletterRepository(context = context,navController), )
    val newsCount by dashboardViewModel.dashboardList.observeAsState(emptyList<SabencosNewsLetterDashCountDomain>())


    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp),
        Modifier.background(color = SabencosYellow).fillMaxWidth().fillMaxHeight(),) {

    if (!newsCount.isNullOrEmpty()){
        items(newsCount!!){ news->
            if(news != null){
                DashboardCountView(news.name,news.total)
            }

        }
    }

    }
//    newsCount?.forEach { news->
//
//        DashboardCountView(
//            news?.name,
//            count = news?.total
//        )
//
//    }

}
//TODO: Remove this temp function
@Composable
fun DashboardCountView( name:String?,count:String?,){
    //Card() {
        Column {
            if(!count.isNullOrEmpty()){
                Text(count)
            }
            if (!name.isNullOrEmpty()){
                Text(name)
            }
        }
    //}



}