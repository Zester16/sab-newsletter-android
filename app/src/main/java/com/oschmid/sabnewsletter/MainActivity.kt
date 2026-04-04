package com.oschmid.sabnewsletter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.oschmid.sabnewsletter.ui.theme.SabNewsLetterTheme
import com.oschmid.sabnewsletter.views.navdrawer.NavigationDrawerApp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = getApplicationContext()
        //enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            // .padding(30.dp)
            SabNewsLetterTheme {
//                Surface(modifier = Modifier
//                    .fillMaxSize(), color = Color.White
//
//                ) {
//                    NavGraph(context=context,navController = navController)
//
//                }

                NavigationDrawerApp(context, navController = navController)
            }

        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SabNewsLetterTheme {
        Greeting("Android")
    }
}