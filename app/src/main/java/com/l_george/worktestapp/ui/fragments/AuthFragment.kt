package com.l_george.worktestapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.l_george.worktestapp.R
import com.l_george.worktestapp.databinding.FragmentAuthBinding
import com.l_george.worktestapp.ui.viewModels.LogInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    private val logInViewModel: LogInViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(layoutInflater, container, false)



        with(binding) {
            buttonComplete.setOnClickListener {
                logInViewModel.logIn(inputLogin.text.toString(), inputPassword.text.toString())
            }


            logInViewModel.isAuthLiveData.observe(viewLifecycleOwner) {
                if (it){
                    findNavController().navigate(R.id.action_authFragment_to_paymentFragment)
                }
            }
        }

        return binding.root
    }


}