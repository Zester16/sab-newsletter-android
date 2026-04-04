package com.oschmid.sabnewsletter.views.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.oschmid.sabnewsletter.navigation.NavigationConstant
import com.oschmid.sabnewsletter.network.SabencosAuthentication
import com.oschmid.sabnewsletter.network.UserLoginRequest
import com.oschmid.sabnewsletter.sharedprefrence.UserAuthSharedPrefrence
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await

class LoginViewModel(context: Context) : ViewModel() {

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

    fun getSetCredentials(username: String, password: String, navController: NavController) {
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
                    viewmodelScope.launch(Dispatchers.Main) {
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