package com.example.sabnewsletter.views.login



import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.sabnewsletter.navigation.NavigationConstant
import com.example.sabnewsletter.R

@Composable
fun LoginView(navController: NavController,viewModel: LoginViewModel = LoginViewModel(LocalContext.current)){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
        val sabencosIcon = painterResource(id = R.drawable.ic_launcher_round)
        val isAuthenticated by viewModel.isAuthenticate.observeAsState(false)
        var inputUsername by remember { mutableStateOf("") }
        var inputPassword by remember { mutableStateOf("") }
        val context=LocalContext.current

        Image(painter = sabencosIcon, contentDescription = "sabencos news logo")
        TextField(value = inputUsername, onValueChange ={inputUsername =it},label = {Text(text = "Add Username")}, )
        TextField(value = inputPassword, onValueChange ={inputPassword =it},
            label = {Text(text = "Add Password")},
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password) )
        Button(onClick = {
            Log.v("input-username",inputUsername)
            if(!inputUsername.isEmpty() && !inputPassword.isEmpty()){
                viewModel.getSetCredentials(inputUsername,inputPassword)
            }else{
                Toast.makeText(context,"Check whether password is added properly and try again",Toast.LENGTH_SHORT).show()
            }
            }) {
            Text(text = "Click to login")     
        }

        if(isAuthenticated){
            navController.navigate(NavigationConstant.DASHOBARD){
                launchSingleTop = true
                popUpTo(0)

            }
        }
    }
    
}