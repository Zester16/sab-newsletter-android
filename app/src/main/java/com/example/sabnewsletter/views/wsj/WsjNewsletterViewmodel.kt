package com.example.sabnewsletter.views.wsj

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sabnewsletter.domain.SabencosNewsletersDomain
import com.example.sabnewsletter.repository.CheckRepository
import com.example.sabnewsletter.repository.SabencosNewsletterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class WsjNewsletterViewmodel(private val newsletterRepository: SabencosNewsletterRepository) {

    private val _newsletterList = MutableLiveData<List<SabencosNewsletersDomain?>?>()
    val newsletterList:LiveData<List<SabencosNewsletersDomain?>?>
        get() = _newsletterList

    private val job = Job()
    private val coroutineJob = CoroutineScope(Dispatchers.Main+job)


    init{
        getNewsletters()
    }

    private fun getNewsletters(){
        coroutineJob.launch(Dispatchers.IO){
               val response= newsletterRepository.getWSJNewsletters()
                _newsletterList.postValue(response)

        }
    }
}