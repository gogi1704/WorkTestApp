package com.l_george.worktestapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.l_george.worktestapp.R
import com.l_george.worktestapp.databinding.FragmentPaymentBinding
import com.l_george.worktestapp.exception.ApiException
import com.l_george.worktestapp.exception.AuthException
import com.l_george.worktestapp.exception.NetworkException
import com.l_george.worktestapp.exception.UnknownException
import com.l_george.worktestapp.ui.recyclerAdapters.PaymentAdapter
import com.l_george.worktestapp.ui.viewModels.LogInViewModel
import com.l_george.worktestapp.ui.viewModels.PaymentsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : Fragment() {
    private lateinit var binding: FragmentPaymentBinding
    private val paymentViewModel: PaymentsViewModel by viewModels()
    private val loginViewModel: LogInViewModel by viewModels()
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

            buttonSignOut.setOnClickListener {
                loginViewModel.signOut()
            }

            paymentViewModel.paymentListLiveData.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }

            loginViewModel.isAuthLiveData.observe(viewLifecycleOwner) {
                if (!it) {
                    findNavController().navigateUp()
                }
            }

            paymentViewModel.appStateLiveData.observe(viewLifecycleOwner) {
                progressBar.visibility = if (it.isLoad) View.VISIBLE else View.GONE
                when (it.exception) {
                    is ApiException -> makeToast(getString(R.string.server_toast))
                    is AuthException -> makeToast(getString(R.string.auth_toast))
                    is NetworkException -> makeToast(getString(R.string.network_toast))
                    is UnknownException -> makeToast(getString(R.string.unknown_toast))
                    null -> {}
                }
            }


        }

        val backCallBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {}

        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backCallBack)

        return binding.root
    }


}