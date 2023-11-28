package com.l_george.worktestapp.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.l_george.worktestapp.R
import com.l_george.worktestapp.databinding.FragmentAuthBinding
import com.l_george.worktestapp.exception.ApiException
import com.l_george.worktestapp.exception.AuthException
import com.l_george.worktestapp.exception.NetworkException
import com.l_george.worktestapp.exception.UnknownException
import com.l_george.worktestapp.ui.viewModels.LogInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    private val logInViewModel: LogInViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(layoutInflater, container, false)

        with(binding) {
            buttonComplete.setOnClickListener {
                logInViewModel.logIn(inputLogin.text.toString(), inputPassword.text.toString())
            }

            logInViewModel.appStateLiveData.observe(viewLifecycleOwner) {
                progressBar.visibility = if (it.isLoad)View.VISIBLE else View.GONE
                when (it.exception) {
                    is ApiException -> createToast(requireContext(), "Server error. Try later.")
                    is AuthException -> {
                        inputLogin.error = "check login"
                        inputPassword.error = "check password"
                        createToast(requireContext(), "Invalid login or password")
                    }
                    is NetworkException -> createToast(requireContext(), "Network error. Check internet connection.")
                    is UnknownException -> createToast(requireContext(), "Unknown error")
                    null -> {}
                }
            }


            logInViewModel.isAuthLiveData.observe(viewLifecycleOwner) {
                if (it) {
                    findNavController().navigate(R.id.action_authFragment_to_paymentFragment)
                }
            }
        }

        return binding.root
    }


}

private fun createToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}