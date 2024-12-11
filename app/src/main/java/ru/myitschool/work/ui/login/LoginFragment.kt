package ru.myitschool.work.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.myitschool.work.R
import ru.myitschool.work.core.LoginState
import ru.myitschool.work.databinding.FragmentLoginBinding
import ru.myitschool.work.ui.main.MainDestination
import ru.myitschool.work.utils.collectWhenStarted
import ru.myitschool.work.utils.visibleOrGone

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)

        binding.login.isEnabled = false
        setupInputValidation()
        subscribe()

        binding.login.setOnClickListener {
            val username = binding.username.text.toString()
            viewModel.login(username)
            viewModel.state.collectWhenStarted(this) { state ->
                if (state is LoginState.Success) {
                    findNavController().navigate(MainDestination)
                }
            }
        }
    }

    private fun setupInputValidation() {
        binding.username.addTextChangedListener {
            val username = it.toString().trim()
            binding.login.isEnabled = isInputValid(username)
        }
    }

    private fun isInputValid(username: String): Boolean {
        return username.isNotBlank() &&
                username.length >= 3 &&
                !username.first().isDigit() &&
                username.all { it.isLetterOrDigit() }
    }

    private fun subscribe() {
        viewModel.state.collectWhenStarted(this) { state ->
            binding.loading.visibleOrGone(state is LoginState.Loading)
            binding.error.visibleOrGone(state is LoginState.Error)
            if (state is LoginState.Error) {
                binding.error.text = state.message
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
