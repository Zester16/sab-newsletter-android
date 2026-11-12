package com.oschmid.sabnewsletter.views.national

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.oschmid.sabnewsletter.domain.SabencosNewsletersDomain
import com.oschmid.sabnewsletter.repository.CheckRepository
import com.oschmid.sabnewsletter.repository.SabencosNewsletterRepository
import com.oschmid.sabnewsletter.views.bloomberg.BloombergViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NationalViewModel(private val newsletterRepository: SabencosNewsletterRepository):ViewModel(){

    private val _newslettersList = MutableLiveData<List<SabencosNewsletersDomain?>?>()
    val newsletterList:LiveData<List<SabencosNewsletersDomain?>?>
        get() = _newslettersList

    //loader UI
    private val _isLoading = MutableLiveData<Boolean>();
    val isLoading : LiveData<Boolean>
        get() = _isLoading
    //threads
    private val job = Job()
    private val coroutineJob = CoroutineScope(Dispatchers.Main+job)

    init{
        getNewsLetters()
    }

    fun getNewsLetters() {
        coroutineJob.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val response = newsletterRepository.getNationalNews()
            _newslettersList.postValue(response)
            _isLoading.postValue(false)
            Log.v("sabDash", response.toString())
        }
    }
}

class NationalViewModelFactory(private val newsletterRepository:SabencosNewsletterRepository):ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T =
        NationalViewModel( newsletterRepository) as T
}