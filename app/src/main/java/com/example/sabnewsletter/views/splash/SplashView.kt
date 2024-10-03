package com.example.sabnewsletter.views.splash

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sabnewsletter.repository.AuthenticationRepository

@Composable
fun SplashView(navController: NavController, viewModel: SplashViewModel =viewModel(
    factory = SplashViewModelFactory(authenticationRepository = AuthenticationRepository(LocalContext.current,navController))
)) {
    Log.v("composable splash","splash")
    Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

    Text(text = "LOADING!!!!!!!!!!!", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
    //val authenticationRepository = AuthenticationRepository(LocalContext.current,navController)
    //authenticationRepository.checkIsUserLoggedIn()
}
}