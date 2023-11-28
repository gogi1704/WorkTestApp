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
                    is ApiException -> createToast(requireContext(), getString(R.string.server_toast))
                    is AuthException -> {
                        inputLogin.error = getString(R.string.login_error_hint)
                        inputPassword.error = getString(R.string.password_error_hint)
                        createToast(requireContext(), getString(R.string.auth_toast))
                    }
                    is NetworkException -> createToast(requireContext(), getString(R.string.network_toast))
                    is UnknownException -> createToast(requireContext(), getString(R.string.unknown_toast))
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