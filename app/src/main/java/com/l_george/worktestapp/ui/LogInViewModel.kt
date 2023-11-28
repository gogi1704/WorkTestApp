package com.l_george.worktestapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.l_george.worktestapp.data.models.UserModel
import com.l_george.worktestapp.repository.logInRepository.LogInRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LogInViewModel @Inject constructor(application: Application  , private val repository: LogInRepositoryImpl) : AndroidViewModel(application) {

    fun logIn(login:String , password:String){
        viewModelScope.launch {
           repository.logIn(UserModel(login , password))
        }
    }



    init {
        logIn("demo" , "12345")
    }

}

