

package com.example.sabnewsletter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sabnewsletter.navigation.NavGraph
import com.example.sabnewsletter.navigation.NavigationConstant
import com.example.sabnewsletter.ui.theme.SabNewsLetterTheme
import com.example.sabnewsletter.views.navdrawer.NavigationDrawerApp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context=getApplicationContext()
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