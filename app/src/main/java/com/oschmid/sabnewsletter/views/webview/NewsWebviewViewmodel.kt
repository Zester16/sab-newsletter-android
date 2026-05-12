package com.oschmid.sabnewsletter.views.webview

import android.util.Log
import androidx.lifecycle.ViewModel
import com.oschmid.sabnewsletter.data.UserReadDatasource
import com.oschmid.sabnewsletter.repository.SabencosUserEngineRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NewsWebviewViewmodel(val sabencosUserEngineRepository: SabencosUserEngineRepository):ViewModel() {

    private val job =  Job()
    private val viewemodelJob= CoroutineScope(Dispatchers.Main + job)


    fun addNewsread(userReadDatasource: UserReadDatasource){
        viewemodelJob.launch(Dispatchers.IO) {

           val result =  sabencosUserEngineRepository.addUserRead(userReadDatasource)
            Log.v("NewsWebViewModel",result.toString())
        }

        }

}

