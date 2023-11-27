package com.l_george.worktestapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.l_george.worktestapp.data.UserModel
import com.l_george.worktestapp.repository.LogInRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LogInViewModel @Inject constructor(application: Application  , private val repository: LogInRepositoryImpl) : AndroidViewModel(application) {
    var str = ""
        set(value) {
            field = value
            strLiveData.value = value
        }
    val strLiveData = MutableLiveData(str)
    fun logIn(){
        viewModelScope.launch {
            str = repository.logIn(UserModel("demo" , "12345"))
        }
    }

    init {
        logIn()
    }
}