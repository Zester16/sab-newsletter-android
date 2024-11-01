package com.example.sabnewsletter.views.navdrawer

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import androidx.navigation.NavHostController

import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sabnewsletter.R
import com.example.sabnewsletter.navigation.NavGraph
import com.example.sabnewsletter.navigation.NavigationConstant
import com.example.sabnewsletter.repository.AuthenticationRepository

import com.example.sabnewsletter.views.usernavigation.UserNavigationBottomSheet


import kotlinx.coroutines.launch
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerApp(context: Context,navController: NavHostController,viewmodel:NavigationDrawerViewModel=NavigationDrawerViewModel(),authenticationRepository: AuthenticationRepository=AuthenticationRepository(context,navController)) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                //Text("Drawer title", modifier = Modifier.padding(16.dp))
                val sabencosIcon = painterResource(id = R.drawable.ic_launcher_round)
                Row {
                    Image(painter = sabencosIcon, contentDescription = "sabencos logo", modifier = Modifier
                        .width(90.dp)
                        .height(90.dp), alignment = Alignment.Center)
                    IconButton(onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                    }) {
                        Icon(  //Show Menu Icon on TopBar
                            imageVector = Icons.Default.Close,
                            contentDescription = "Menu"
                        )
                    }
                }

                NavigationDrawerItem(
                    label = { Text(text = "Drawer Item") },
                    selected = false,
                    onClick = { /*TODO*/ }
                )
            }

        }, gesturesEnabled = false,drawerState = drawerState){
        Scaffold(
            topBar = {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute =
                    navBackStackEntry?.destination?.route

                //to hide bottomNavBar
                val skipNav = listOf(NavigationConstant.SPLASH,NavigationConstant.LOGIN)
                if (currentRoute in skipNav) return@Scaffold

                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        //Text("Sabencos News Letter")
                    },
                    navigationIcon = {
                        IconButton(onClick = {scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }}) {
                            Icon(  //Show Menu Icon on TopBar
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { viewmodel.showBottomSheet()}) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "Localized description"
                            )
                        }
                    }

                    )
            },
        ){innerPadding->
            Surface(modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(), color = Color.White

            ) {
                NavGraph(context=context,navController = navController)
                UserNavigationBottomSheet(viewmodel, authenticationRepository = authenticationRepository )
            }
        }
    }
}
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MainScreenNavigationDrawer( context: Context,navController: NavHostController) {
//    //val scaffoldState = rememberScaffoldState()
//    val scope = rememberCoroutineScope()
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    titleContentColor = MaterialTheme.colorScheme.primary,
//                ),
//                title = {
//                    Text("Small Top App Bar")
//                }
//            )
//        },
//    ) { innerPadding ->
//        //ScrollContent(innerPadding)
//        Column(modifier = Modifier.padding(innerPadding)) {
//            NavHost(
//                navController = navController,
//                startDestination = NavigationConstant.BLM_VIEW
//            ) {
//                //NavGraph(context=context,navController = navController)
//                        composable(NavigationConstant.BLM_VIEW){
//            DashboardView(context,navController)
//        }
//            }
//        }
//    }
//}




