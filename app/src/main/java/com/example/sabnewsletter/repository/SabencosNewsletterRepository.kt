package com.example.sabnewsletter.repository

import android.content.Context
import android.util.Log
import androidx.navigation.NavController
import com.example.sabnewsletter.domain.SabencosNewsLetterDashCountDomain
import com.example.sabnewsletter.domain.SabencosNewsletersDomain
import com.example.sabnewsletter.domain.SabencosNewsletterImagelessDomain
import com.example.sabnewsletter.network.SabencosNewslettersObject
import com.example.sabnewsletter.network.sabencosNLApi.toSabencosNewsDashDomain
import com.example.sabnewsletter.network.toNewsLetterDatasource
import com.example.sabnewsletter.network.toNewsLetterImagelessDatasource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await

/**
 * Primary aim of this class is to act as an intermediatory to fetch data annonymously
 * it will fetch all current newsletters
 * currently its just bloomberg prime
 * */
class SabencosNewsletterRepository(private val context: Context, private val navController: NavController) {

    private val authRepository=AuthenticationRepository(context,navController)
    private val sabencosNewsletters: SabencosNewslettersObject
        get() {
            return SabencosNewslettersObject

        }

    suspend fun getNewsletterDashCount():List<SabencosNewsLetterDashCountDomain?>?{
        return withContext(Dispatchers.IO){
            try{
                val headers = authRepository.getAuthHeaders(true)
               val  response =sabencosNewsletters.sabencosNewsletters.getAllNewsCount(headers = headers).await()
                return@withContext response.data.toSabencosNewsDashDomain()
            }catch (exception:Exception){
                Log.v("SabNewsLetterRepository:getNewsletterDashCount",exception.toString())
                val respose =authRepository.checkAuthErrorAndTakeAction(exception)
                if(respose){
                    Log.v("SabNewsLetterRepository",respose.toString())
                    return@withContext getNewsletterDashCount()
                }
                return@withContext null
            }
        }
    }
    suspend fun getBloombergNews(): List<SabencosNewsletersDomain>? {
        return withContext(Dispatchers.IO) {
            try {
                Log.v("in bml get news","to getnews")
                val headers = authRepository.getAuthHeaders(true)
                val response=sabencosNewsletters.sabencosNewsletters.getBloombergNewsletter(headers = headers).await()
                return@withContext response.toNewsLetterDatasource()
            }
            catch(exception:Exception){
                Log.v("SNRepository:exception",exception.toString())
                val respose =authRepository.checkAuthErrorAndTakeAction(exception)
                if(respose){
                    Log.v("SabNewsLetterRepository",respose.toString())
                    return@withContext getBloombergNews()
                }
                return@withContext null
            }

        }
    }

    suspend fun getLivemintTotMNewsletter():List<SabencosNewsletterImagelessDomain>?{
        return withContext(Dispatchers.IO){
            try{
                val headers = authRepository.getAuthHeaders(refresh = true)
                val response =sabencosNewsletters.sabencosNewsletters.getMintTopOfMorningNewsletters(headers = headers).await()
                return@withContext response.toNewsLetterImagelessDatasource()
            }
            catch(exception:Exception){
                Log.v("SNRepository:exception",exception.toString())
                val respose =authRepository.checkAuthErrorAndTakeAction(exception)
                if(respose){
                    Log.v("SabNewsLetterRepository",respose.toString())
                    return@withContext getLivemintTotMNewsletter()
                }
                return@withContext null
            }
      }
    }
    suspend fun getWSJNewsletters():List<SabencosNewsletersDomain>?{
        return withContext(Dispatchers.IO){
            try{
                val headers = authRepository.getAuthHeaders(refresh = true)
                val response = sabencosNewsletters.sabencosNewsletters.getWsjNewsletters(headers=headers).await()
                return@withContext response.toNewsLetterDatasource()
            }catch(exception:Exception){
                Log.v("SabencosNewsletterRepository",exception.toString())
                val response = authRepository.checkAuthErrorAndTakeAction(exception = exception)

                if(response){
                    return@withContext getWSJNewsletters()
                }
                return@withContext null

            }
        }
    }
}