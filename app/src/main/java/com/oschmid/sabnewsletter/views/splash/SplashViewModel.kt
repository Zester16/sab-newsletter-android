package com.oschmid.sabnewsletter.views.splash


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.oschmid.sabnewsletter.repository.AuthenticationRepository

class SplashViewModel(authenticationRepository: AuthenticationRepository) : ViewModel() {

    init {
        authenticationRepository.checkIsUserLoggedIn()
    }


}

class SplashViewModelFactory(private val authenticationRepository: AuthenticationRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T =
        SplashViewModel(authenticationRepository) as T
}