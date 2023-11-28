package com.l_george.worktestapp.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.l_george.worktestapp.data.models.UserModel
import com.l_george.worktestapp.exception.ApiException
import com.l_george.worktestapp.exception.AppState
import com.l_george.worktestapp.exception.AuthException
import com.l_george.worktestapp.exception.NetworkException
import com.l_george.worktestapp.exception.UnknownException
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


    private var appState = AppState()
        set(value) {
            field = value
            appStateLiveData.value = value
        }
    val appStateLiveData = MutableLiveData(appState)


    fun logIn(login: String, password: String) {
        appState = appState.copy(isLoad = true)
        viewModelScope.launch {
            try {
                isAuthLiveData.value = repository.logIn(UserModel(login, password))
            } catch (network: NetworkException) {
                appState = appState.copy(exception = network)
            } catch (api: ApiException) {
                appState = appState.copy(exception = api)
            } catch (auth: AuthException) {
                appState = appState.copy(exception = auth)
            } catch (unknown: UnknownException) {
                appState = appState.copy(exception = unknown)
            }
            appState = AppState()

        }
    }


}

