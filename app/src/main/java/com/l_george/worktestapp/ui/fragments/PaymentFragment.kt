package com.l_george.worktestapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.l_george.worktestapp.databinding.FragmentPaymentBinding
import com.l_george.worktestapp.ui.recyclerAdapters.PaymentAdapter
import com.l_george.worktestapp.ui.viewModels.PaymentsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : Fragment() {
    private lateinit var binding: FragmentPaymentBinding
    private val paymentViewModel: PaymentsViewModel by viewModels()
    private lateinit var adapter: PaymentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = PaymentAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentBinding.inflate(layoutInflater, container, false)

        with(binding) {
            recyclerPayments.adapter = adapter


        }



        return binding.root
    }


}