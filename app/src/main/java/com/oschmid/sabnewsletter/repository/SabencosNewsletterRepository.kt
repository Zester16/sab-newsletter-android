package com.oschmid.sabnewsletter.repository

import android.content.Context
import android.util.Log
import androidx.navigation.NavController
import com.oschmid.sabnewsletter.data.UserNewsReadDatasource
import com.oschmid.sabnewsletter.domain.SabencosNewsLetterDashCountDomain
import com.oschmid.sabnewsletter.domain.SabencosNewsletersDomain
import com.oschmid.sabnewsletter.domain.SabencosNewsletterImagelessDomain
import com.oschmid.sabnewsletter.network.SabencosNewslettersObject
import com.oschmid.sabnewsletter.network.sabencosNLApi.toSabencosNewsDashDomain
import com.oschmid.sabnewsletter.network.toNewsLetterDatasource
import com.oschmid.sabnewsletter.network.toNewsLetterImagelessDatasource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await

/**
 * Primary aim of this class is to act as an intermediatory to fetch data annonymously
 * it will fetch all current newsletters
 * currently its just bloomberg prime
 * */
class SabencosNewsletterRepository(
    private val context: Context,
    private val navController: NavController
) {

    private val authRepository = AuthenticationRepository(context, navController)
    private val sabencosUserEngineRepository = SabencosUserEngineRepository(context = context, navController = navController)
    private val sabencosNewsletters: SabencosNewslettersObject
        get() {
            return SabencosNewslettersObject

        }

    suspend fun getNewsletterDashCount(): List<SabencosNewsLetterDashCountDomain?>? {
        return withContext(Dispatchers.IO) {
            try {
                val headers = authRepository.getAuthHeaders(true)
                val response =
                    sabencosNewsletters.sabencosNewsletters.getAllNewsCount(headers = headers)
                        .await()
                return@withContext response.data.toSabencosNewsDashDomain()
            } catch (exception: Exception) {
                Log.v("SabNewsLetterRepository:getNewsletterDashCount", exception.toString())
                val respose = authRepository.checkAuthErrorAndTakeAction(exception)
                if (respose) {
                    Log.v("SabNewsLetterRepository", respose.toString())
                    return@withContext getNewsletterDashCount()
                }
                return@withContext null
            }
        }
    }

    suspend fun getBloombergNews(): List<SabencosNewsletersDomain>? {
        return withContext(Dispatchers.IO) {
            try {
                Log.v("in bml get news", "to getnews")
                val headers = authRepository.getAuthHeaders(true)
                val response =
                    sabencosNewsletters.sabencosNewsletters.getBloombergNewsletter(headers = headers)
                        .await().toNewsLetterDatasource()
                val userNewsreads = sabencosUserEngineRepository.getUserNewsReads("bl-ns")

                //Log.v("SNRepositoryNewsReads",userNewsreads.size.toString())

                return@withContext setNewsletterDomainForNewsRead(userNewsReads = userNewsreads, newsletters = response)
            } catch (exception: Exception) {
                Log.v("SNRepository:exception", exception.toString())
                val respose = authRepository.checkAuthErrorAndTakeAction(exception)
                if (respose) {
                    Log.v("SabNewsLetterRepository", respose.toString())
                    return@withContext getBloombergNews()
                }
                return@withContext null
            }

        }
    }

    suspend fun getLivemintTotMNewsletter(): List<SabencosNewsletterImagelessDomain>? {
        return withContext(Dispatchers.IO) {
            try {
                val headers = authRepository.getAuthHeaders(refresh = true)
                val response =
                    sabencosNewsletters.sabencosNewsletters.getMintTopOfMorningNewsletters(headers = headers)
                        .await()
                return@withContext response.toNewsLetterImagelessDatasource()
            } catch (exception: Exception) {
                Log.v("SNRepository:exception", exception.toString())
                val respose = authRepository.checkAuthErrorAndTakeAction(exception)
                if (respose) {
                    Log.v("SabNewsLetterRepository", respose.toString())
                    return@withContext getLivemintTotMNewsletter()
                }
                return@withContext null
            }
        }
    }

    suspend fun getWSJNewsletters(): List<SabencosNewsletersDomain>? {
        return withContext(Dispatchers.IO) {
            try {
                val headers = authRepository.getAuthHeaders(refresh = true)
                val response =
                    sabencosNewsletters.sabencosNewsletters.getWsjNewsletters(headers = headers)
                        .await()
                Log.v("snlRepo:WSJ:RawREsp", response?.size.toString())
                return@withContext response.toNewsLetterDatasource()
            } catch (exception: Exception) {
                Log.v("SabencosNewsletterRepository", exception.toString())
                val response = authRepository.checkAuthErrorAndTakeAction(exception = exception)

                if (response) {
                    return@withContext getWSJNewsletters()
                }
                return@withContext null

            }
        }


    }

    //This function will iterate through both list and
    //currently it takes 4.94 seconds to hit both downstreams and get data
    private fun setNewsletterDomainForNewsRead(userNewsReads:List<UserNewsReadDatasource>?,newsletters:List<SabencosNewsletersDomain>?):List<SabencosNewsletersDomain>?{
        if(userNewsReads.isNullOrEmpty()){
            return newsletters
        }
        if(newsletters.isNullOrEmpty()){
            return newsletters
        }
        val newsReadIterator:ListIterator<UserNewsReadDatasource> = userNewsReads.listIterator()
        val newslettersIterator:ListIterator<SabencosNewsletersDomain> = newsletters.listIterator()
        var newsletter = newslettersIterator.next()
        var newsRead = newsReadIterator.next()
        var newsletterIndex = newslettersIterator.nextIndex()

        //TODO: Add iterator and update value
        while (newslettersIterator.hasNext() && newsReadIterator.hasNext())
        {

            if(newsRead.newsId == newsletter.id) {
                newsletters[newsletterIndex].status = newsRead.status
                newsRead = newsReadIterator.next()
                Log.v("snlStatus",newsletter.title.toString()+newsletterIndex.toString())
            }
            newsletterIndex = newslettersIterator.nextIndex()
            newslettersIterator.next()
            newsletter= newsletters[newsletterIndex]

        }
        return newsletters
    }

}