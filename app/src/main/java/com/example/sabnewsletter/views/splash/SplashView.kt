package com.example.sabnewsletter.views.splash

import android.util.Log
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sabnewsletter.R
import com.example.sabnewsletter.repository.AuthenticationRepository
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun SplashView(navController: NavController, viewModel: SplashViewModel =viewModel(
    factory = SplashViewModelFactory(authenticationRepository = AuthenticationRepository(LocalContext.current,navController))
)) {
    Log.v("composable splash", "splash")
    Row(
        modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

//        Text(
//            text = "LOADING!!!!!!!!!!!",
//            modifier = Modifier.fillMaxWidth(),
//            textAlign = TextAlign.Center
//        )
        AnimatedVectorDrawable()
        //val authenticationRepository = AuthenticationRepository(LocalContext.current,navController)
        //authenticationRepository.checkIsUserLoggedIn()
        // }


    }
}
@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun AnimatedVectorDrawable() {
        val image = AnimatedImageVector.animatedVectorResource(R.drawable.baseline_hourglass)
        var atEnd by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    // This is necessary just if you want to run the animation when the
    // component is displayed. Otherwise, you can remove it.

    suspend fun runAnimation() {
        while (true) {
            delay(8000) // set here your delay between animations
            atEnd = !atEnd
            runAnimation()
        }
    }
    LaunchedEffect(image) {
        atEnd = !atEnd
        runAnimation()
    }
        Image(
            painter = rememberAnimatedVectorPainter(image,atEnd = atEnd),
            contentDescription = "Timer",
            contentScale = ContentScale.FillWidth,
        )
    Text(text = "LOADING")
    }