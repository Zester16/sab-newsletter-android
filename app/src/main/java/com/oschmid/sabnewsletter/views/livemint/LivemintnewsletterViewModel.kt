package com.oschmid.sabnewsletter.views.livemint

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.oschmid.sabnewsletter.domain.SabencosNewsletterImagelessDomain
import com.oschmid.sabnewsletter.repository.CheckRepository
import com.oschmid.sabnewsletter.repository.SabencosNewsletterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class LivemintnewsletterViewModel(
    private val checkRepository: CheckRepository,
    private val newsletterRepository: SabencosNewsletterRepository
) : ViewModel() {

    private val _newsletterList = MutableLiveData<List<SabencosNewsletterImagelessDomain?>?>()
    val newsletterList: LiveData<List<SabencosNewsletterImagelessDomain?>?>
        get() = _newsletterList
    private val job = Job()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val viewmodelJob = CoroutineScope(job + Dispatchers.Main)

    override fun onCleared() {
        viewmodelJob.cancel()
        super.onCleared()
    }

    init {
        getNewsletter()
    }

    fun getNewsletter() {
        viewmodelJob.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val response = newsletterRepository.getLivemintTotMNewsletter()
            _newsletterList.postValue(response)
            _isLoading.postValue(false)
            Log.v("livemint-newsletter", response.toString())
        }
    }
}


class LivemintNewsletterViewmodelFactory(
    private val checkRepository: CheckRepository,
    private val newsletterRepository: SabencosNewsletterRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T =
        LivemintnewsletterViewModel(checkRepository, newsletterRepository) as T

}
