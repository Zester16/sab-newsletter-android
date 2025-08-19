package com.example.sabnewsletter.repository

import android.content.Context
import android.util.Log
import androidx.navigation.NavController
import com.example.sabnewsletter.datasource.AuthDatasource
import com.example.sabnewsletter.navigation.NavigationConstant
import com.example.sabnewsletter.network.SabencosAuthentication
import com.example.sabnewsletter.sharedprefrence.UserAuthSharedPrefrence
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await

class AuthenticationRepository(private val context:Context,private val navController: NavController){
    private val userAuthSharedPrefrence=UserAuthSharedPrefrence(context)
    private val sabencosAuthentication:SabencosAuthentication
        get() {
            return SabencosAuthentication
        }
    private val coroutineScope=CoroutineScope(Dispatchers.Main+Job())

    /**
     * this function is used in splash screen.
     * */
    fun checkIsUserLoggedIn() {
        val authToken=userAuthSharedPrefrence.getTokenAuth()
        val refreshToken = userAuthSharedPrefrence.getTokenRefresh()
        Log.v("token",authToken.toString())
        if(authToken == null || refreshToken==null ){

            navController.navigate(NavigationConstant.LOGIN){
                this.popUpTo(0)
            }
        }
        else{
            Log.v("checkUserLoggedIn","into else")
            coroutineScope.launch() {
                //try{
                val status = checkTokenStatus()
                if(status.normalError==null && status.authError==null){
                    navController.navigate(NavigationConstant.DASHOBARD){
                        this.popUpTo(0)
                    }

                }
                if(status.authError==true){
                    logOutUser()
                }
            }

        }

    }
    fun logOutUser(){
        userAuthSharedPrefrence.removeTokens()
        navController.navigate(NavigationConstant.LOGIN){
            popUpTo(0)
        }
    }
    private suspend fun getRefreshToken():AuthDatasource{
        return withContext(Dispatchers.IO){
            try {
                val authToken=userAuthSharedPrefrence.getTokenAuth()!!
                val headers=mapOf("Authorization" to authToken)
                val response=sabencosAuthentication.authenticationApi.refreshToken(headers).await()
                Log.v("AuthenticationRepository","got refresh token")
                userAuthSharedPrefrence.setTokenRefresh(response.ref)
                return@withContext AuthDatasource()

            }
            catch(exception:Exception){
                Log.v("refresh-tk-exception",exception.toString())
                if(exception.message.toString() == "HTTP 401 Unauthorized"){
                    //logOutUser()
                    return@withContext AuthDatasource(authError = true)
                }
                return@withContext AuthDatasource(normalError = true)
            }
        }

    }
    fun getAuthHeaders(refresh:Boolean):Map<String,String>{
        var token:String
        if(refresh){
            token=userAuthSharedPrefrence.getTokenRefresh()!!

        }
        else{
            token=userAuthSharedPrefrence.getTokenAuth()!!
        }

        return mapOf("Authorization" to token)
    }

    /**
     * simple as it sounds, check error checks if error is due to authorization/token expired
     * it gets refreshed token. if refresh token too throws error
     * this function will return AuthDatasource based on that logic
     * it does not handle loggin user in or logging out
     * */
    private suspend fun checkErrors(exception:Exception):AuthDatasource{
        //coroutineScope.launch{
        Log.v("AuthenticationRepository",exception.message.toString())
        if(exception.message.toString() == "HTTP 401 Unauthorized"){
            val response =getRefreshToken()
            Log.v("AuthenticationRepository","r3efresh done"+response.toString())
            return response
            //if(response){
            //checkIsUserLoggedIn()
            // }
        }
        return AuthDatasource(normalError = true)
        //}
    }
    /**
     * primarly used by checkIsUserLoggedIN to check if user is logged in or not
     * the logical action from return type is taken by function
     *
     * */
    suspend fun checkAuthErrorAndTakeAction(exception:Exception):Boolean{
        //coroutineScope.launch(){
        Log.v("AuthenticationRep:CAEATA:Error",exception.message.toString())
        if(exception.message.toString() == "HTTP 401 Unauthorized"){
            val response =getRefreshToken()
            //val response=getRefreshTokenAsync()
            Log.v("AuthenticationRep:CAEATA","r3efresh done"+response.toString())

            if(response.authError==true){
                coroutineScope.launch(Dispatchers.Main
                ) {
                    logOutUser()
                }
            }
            return true

        }
        return false
    }
    private suspend fun checkTokenStatus():AuthDatasource{
        return withContext(Dispatchers.IO){
            try{
                val headers=getAuthHeaders(refresh = true)
                sabencosAuthentication.authenticationApi.testGet(headers).await()
                return@withContext AuthDatasource()
            }
            catch(exception:Exception){

                return@withContext checkErrors(exception)
            }
        }


    }
}