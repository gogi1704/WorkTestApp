package com.l_george.worktestapp.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.l_george.worktestapp.data.models.UserModel
import com.l_george.worktestapp.repository.logInRepository.LogInRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    application: Application,
    private val repository: LogInRepositoryImpl
) : AndroidViewModel(application) {

    val isAuthLiveData = MutableLiveData(repository.isAuth)
    private var loadState = false
        set(value) {
            field = value
            loadStateLiveData.value = value
        }
    val loadStateLiveData = MutableLiveData(loadState)



    fun logIn(login: String, password: String) {
        loadState = true
        viewModelScope.launch {
            try {
               isAuthLiveData.value = repository.logIn(UserModel(login, password))
            } catch (e: Exception) {

            }
            loadState = false
        }
    }


}

