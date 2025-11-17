package com.example.sabnewsletter.views.bloomberg

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.sabnewsletter.domain.SabencosNewsletersDomain
import com.example.sabnewsletter.repository.CheckRepository
import com.example.sabnewsletter.repository.SabencosNewsletterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class BloombergViewModel(private val checkRepository: CheckRepository,private val newsletterRepository: SabencosNewsletterRepository):ViewModel() {
    //all dashboard variables
    private val _newsletterList= MutableLiveData<List<SabencosNewsletersDomain?>?>()
    val newsletterList: MutableLiveData<List<SabencosNewsletersDomain?>?>
        get() = _newsletterList

    //threads
    private val job = Job()
    private val coroutineJob = CoroutineScope(Dispatchers.Main+job)
    override fun onCleared() {
        coroutineJob.cancel()
        super.onCleared()
    }

init{
    getNewsLetters()
}

    fun checkToken(){
        coroutineJob.launch {
            checkRepository.getToken()
        }

    }

    fun getNewsLetters(){
        coroutineJob.launch(Dispatchers.IO) {
            val response=   newsletterRepository.getBloombergNews()
            _newsletterList.postValue(response)
            Log.v("sabDash",response.toString())
        }
    }


}
class BloombergViewModelFactory(private val checkRepository: CheckRepository,private val newsletterRepository: SabencosNewsletterRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T = BloombergViewModel(checkRepository,newsletterRepository) as T
}