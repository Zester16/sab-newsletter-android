package com.example.sabnewsletter.views.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.sabnewsletter.network.SabencosAuthentication
import com.example.sabnewsletter.network.UserLoginRequest
import com.example.sabnewsletter.sharedprefrence.UserAuthSharedPrefrence
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await
import retrofit2.awaitResponse
import kotlin.coroutines.coroutineContext
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.sabnewsletter.navigation.NavigationConstant

class LoginViewModel(context:Context): ViewModel() {

    private val _isAuthenticated = MutableLiveData<Boolean>()
    val isAuthenticate: LiveData<Boolean>
        get() = _isAuthenticated
    private val job = Job()
    private val viewmodelScope = CoroutineScope(Dispatchers.Main + job)
    private val userSharedPrefrence = UserAuthSharedPrefrence(context)
    override fun onCleared() {
        viewmodelScope.cancel()
        super.onCleared()
    }

    fun getSetCredentials(username: String, password: String,navController:NavController) {
        _isAuthenticated.value = false
        viewmodelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.IO) {
                try {
                    //val newUser=UserLoginRequest(username = "zesterschmid16@gmail.com", password = "39913061@Harsh")
                    val newUser = UserLoginRequest(username = username, password = password)
                    val response = SabencosAuthentication.authenticationApi.loginUser(
                        newUser.username,
                        password = newUser.password
                    ).await()
                    userSharedPrefrence.setTokenAuth(response.auth)
                    userSharedPrefrence.setTokenRefresh(response.ref)
                    //_isAuthenticated.postValue(true)
                    viewmodelScope.launch(Dispatchers.Main){
                        navController.navigate(NavigationConstant.DASHOBARD) {
                            launchSingleTop = true
                            popUpTo(0) { inclusive = true }
                        }
                    }
                    Log.v("Login Response", response.toString())
                } catch (exception: Exception) {
                    Log.v("login error", exception.toString())

                    //}
                }
            }
        }

    }
}