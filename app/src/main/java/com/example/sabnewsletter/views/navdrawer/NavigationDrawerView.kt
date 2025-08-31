package com.example.sabnewsletter.views.navdrawer

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import com.example.sabnewsletter.ui.theme.SabencosBlue
import com.example.sabnewsletter.ui.theme.SabencosYellow
import com.example.sabnewsletter.ui.theme.fugazOne

import com.example.sabnewsletter.views.usernavigation.UserNavigationBottomSheet


import kotlinx.coroutines.launch
import androidx.compose.foundation.Image as ImageVector

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerApp(context: Context,navController: NavHostController,viewmodel:NavigationDrawerViewModel=NavigationDrawerViewModel(),authenticationRepository: AuthenticationRepository=AuthenticationRepository(context,navController)) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val menuIcon = painterResource(id = R.drawable.baseline_menu_24)
    //val personIcon = painterResource(id = R.drawable.baseline_person_24)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute =
        navBackStackEntry?.destination?.route
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                //Text("Drawer title", modifier = Modifier.padding(16.dp))
                val sabencosIcon = painterResource(id = R.drawable.ic_launcher_round)
                Row {
                    ImageVector(painter = sabencosIcon, contentDescription = "sabencos logo", modifier = Modifier
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
                    label = { Text(text = "Bloomberg") },
                    selected = currentRoute===NavigationConstant.DASHOBARD,
                    onClick = { navController.navigate(NavigationConstant.DASHOBARD)
                        scope.launch {
                            drawerState.close()
                        }
                    }
                )

                NavigationDrawerItem(
                    label = { Text(text = "Livemint Newsletter") },
                    selected = currentRoute===NavigationConstant.LIVEMINT,
                    onClick = { navController.navigate(NavigationConstant.LIVEMINT)
                        scope.launch {
                            drawerState.close()
                        }
                    }
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
                if (currentRoute in skipNav || currentRoute.isNullOrEmpty()) return@Scaffold

                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = SabencosYellow,
                        titleContentColor = SabencosBlue,

                        ),
                    title = {
                        Text("SNL", fontFamily = fugazOne)
                    },
                    navigationIcon = {
                        IconButton(onClick = {scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }}) {
                            Icon(  //Show Menu Icon on TopBar
                                painter = menuIcon,
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




