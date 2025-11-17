package com.example.sabnewsletter.views.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sabnewsletter.domain.SabencosNewsLetterDashCountDomain
import com.example.sabnewsletter.repository.SabencosNewsletterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class DashboardViewModel(val repository:SabencosNewsletterRepository):ViewModel() {


    private val _dashboardList=MutableLiveData<List<SabencosNewsLetterDashCountDomain?>?>()
    val dashboardList:LiveData<List<SabencosNewsLetterDashCountDomain?>?>
        get() = _dashboardList

    private val job = Job()
    private val viewmodelJob = CoroutineScope(Dispatchers.Main+job)

    override fun onCleared() {
        viewmodelJob.cancel()
        super.onCleared()
    }

    init{
        getDashboardList()
    }

    private fun getDashboardList(){
        viewmodelJob.launch {
           _dashboardList.postValue(repository.getNewsletterDashCount())
        }

    }
}