package com.oschmid.sabnewsletter.repository

import android.content.Context
import android.util.Log
import androidx.navigation.NavController
import com.oschmid.sabnewsletter.data.UserReadDatasource
import com.oschmid.sabnewsletter.network.SabencosUserEngineInterface
import com.oschmid.sabnewsletter.network.SabencosUserEngineRetrofitObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await

class SabencosUserEngineRepository(private val context: Context,
                                   private val navController: NavController
) {
    private val authRepository = AuthenticationRepository(context, navController)
    private val userEngineApi: SabencosUserEngineRetrofitObject
        get() {
           return SabencosUserEngineRetrofitObject
        }
    private val repositoryName="S-U-E-Repo"
    suspend fun addUserRead( userReadDatasource: UserReadDatasource):Boolean{
            val functionName="addUserRead"

            return withContext(Dispatchers.IO){
                try{
                val headers = authRepository.getAuthHeaders(refresh = true)
                val response = userEngineApi.userEngineRetrofitObject.sendUserRead(headers = headers, userReadDatasource = userReadDatasource).await()
                Log.v("${repositoryName}-$functionName}",response.toString())
                if(response.statusCode ==0){
                    return@withContext true
                } else {
                    return@withContext true
                }
                }catch (exception:Exception){
                    Log.v("${repositoryName}-$functionName}",exception.toString())
                    return@withContext false
                }
            }

    }
}