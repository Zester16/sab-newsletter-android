package com.example.sabnewsletter.views.livemint

import android.util.Log
import android.widget.ViewSwitcher.ViewFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.sabnewsletter.domain.SabencosNewsletterImagelessDomain
import com.example.sabnewsletter.repository.CheckRepository
import com.example.sabnewsletter.repository.SabencosNewsletterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class LivemintnewsletterViewModel(private val checkRepository: CheckRepository,private val newsletterRepository: SabencosNewsletterRepository):ViewModel() {

    private val  _newsletterList=MutableLiveData<List<SabencosNewsletterImagelessDomain?>?>()
    val newsletterList:LiveData<List<SabencosNewsletterImagelessDomain?>?>
        get() = _newsletterList
    private val job= Job()

    private val viewmodelJob= CoroutineScope(job+Dispatchers.Main)

    override fun onCleared() {
        viewmodelJob.cancel()
        super.onCleared()
    }

    init{
        getNewsletter()
    }

    fun getNewsletter(){
        viewmodelJob.launch(Dispatchers.IO){
            val response =newsletterRepository.getLivemintTotMNewsletter()
            _newsletterList.postValue(response)
            Log.v("livemint-newsletter",response.toString())
        }
    }
}


class LivemintNewsletterViewmodelFactory(private val checkRepository: CheckRepository,private val newsletterRepository: SabencosNewsletterRepository):ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T =LivemintnewsletterViewModel(checkRepository,newsletterRepository) as T

    }
