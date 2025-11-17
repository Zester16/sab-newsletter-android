package com.example.sabnewsletter.views.wsj

import androidx.lifecycle.LiveData
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
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

// viewmodel for WSJ Newsletters
class WsjNewsletterViewmodel(private val newsletterRepository: SabencosNewsletterRepository):ViewModel() {

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
// viewmodelfactrory for wsjnewsletter
class WsjNewsletterVMFactory(private val newsletterRepository: SabencosNewsletterRepository):ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T=WsjNewsletterViewmodel(newsletterRepository = newsletterRepository) as T

}