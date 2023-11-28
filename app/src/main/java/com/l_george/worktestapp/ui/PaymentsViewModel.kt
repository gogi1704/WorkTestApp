package com.l_george.worktestapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.l_george.worktestapp.repository.paymentsRepository.PaymentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentsViewModel @Inject constructor(
    application: Application,
    private val repository: PaymentsRepository
) : AndroidViewModel(application) {


    fun getPayments() {
        viewModelScope.launch {
            repository.getPayments()
        }
    }
}