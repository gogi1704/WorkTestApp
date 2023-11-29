package com.l_george.worktestapp.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.l_george.worktestapp.exception.ApiException
import com.l_george.worktestapp.exception.AppState
import com.l_george.worktestapp.exception.NetworkException
import com.l_george.worktestapp.exception.UnknownException
import com.l_george.worktestapp.repository.paymentsRepository.PaymentsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentsViewModel @Inject constructor(
    application: Application,
    private val repository: PaymentsRepositoryImpl
) : AndroidViewModel(application) {

    private var appState = AppState()
        set(value) {
            field = value
            appStateLiveData.value = value
        }
    val appStateLiveData = MutableLiveData(appState)

    val paymentListLiveData = repository.paymentsListFlow
        .asLiveData()


    private fun getPayments() {
        appState = appState.copy(isLoad = true)
        viewModelScope.launch {
            try {
                repository.getPayments()
            } catch (io: NetworkException) {
                appState = appState.copy(exception = io)
            } catch (api: ApiException) {
                appState = appState.copy(exception = api)
            } catch (e: UnknownException) {
                appState = appState.copy(exception = e)
            }
            appState = AppState()

        }
    }

    init {
        getPayments()
    }
}