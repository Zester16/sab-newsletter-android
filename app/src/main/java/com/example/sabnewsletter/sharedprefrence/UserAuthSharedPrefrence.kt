package com.example.sabnewsletter.sharedprefrence

import android.content.Context
import android.content.SharedPreferences

class UserAuthSharedPrefrence(val context: Context) {
    companion object{
        const val AUTHSHAREDPREFERENCENAME = "authsharedprefrence"
        const val TOKEN_AUTH="user_auth_token"
        const val TOKEN_REFRESH="user_refresh_auth"
    }
     val sharedPreferences: SharedPreferences= context.getSharedPreferences(
        AUTHSHAREDPREFERENCENAME,0)

        fun getTokenAuth(): String? {

            return sharedPreferences.getString(TOKEN_AUTH,null)
        }

    fun setTokenAuth(token:String){
        val editor = sharedPreferences.edit()
        editor.putString(TOKEN_AUTH,token).apply()
    }

    fun getTokenRefresh(): String? {

        return sharedPreferences.getString(TOKEN_REFRESH,null)
    }

    fun setTokenRefresh(token:String){
        val editor = sharedPreferences.edit()
        editor.putString(TOKEN_REFRESH,token).apply()
    }

    fun removeTokens(){

        val editor = sharedPreferences.edit()
        editor.remove(TOKEN_REFRESH).apply()
        editor.remove(TOKEN_AUTH).apply()
        
    }


}