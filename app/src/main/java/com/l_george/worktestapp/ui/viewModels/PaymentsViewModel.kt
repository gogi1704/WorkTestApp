package com.l_george.worktestapp.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.l_george.worktestapp.data.dataBase.entities.toModel
import com.l_george.worktestapp.repository.paymentsRepository.PaymentsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentsViewModel @Inject constructor(
    application: Application,
    private val repository: PaymentsRepositoryImpl
) : AndroidViewModel(application) {

    val paymentListLiveData = repository.paymentsListFlow
        .flowOn(Dispatchers.Default)
        .map { list ->
            list.map {
                it.toModel()
            }
        }
        .asLiveData()


    private fun getPayments() {
        viewModelScope.launch {
            repository.getPayments()
        }
    }

    init {
        getPayments()
    }
}