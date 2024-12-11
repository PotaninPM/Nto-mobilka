package ru.myitschool.work.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.myitschool.work.R
import ru.myitschool.work.databinding.FragmentMainBinding
import ru.myitschool.work.utils.collectWhenStarted

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        setupUI()
        subscribe()
        viewModel.refreshData()
    }

    private fun setupUI() {
        binding.logout.setOnClickListener {
            viewModel.logout()
            findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        }

        binding.refresh.setOnClickListener {
            viewModel.refreshData()
        }

        binding.scan.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_qrScanFragment)
        }
    }

    private fun subscribe() {
        viewModel.state.collectWhenStarted(this) { state ->
            when (state) {
                is MainState.Success -> {
                    binding.error.visibility = View.GONE
                    binding.fullname.text = state.data.fullName
                    binding.position.text = state.data.position
                    binding.lastEntry.text = state.data.lastEntry
                    //binding.photo.load(state.data.photoUrl)
                    binding.mainContent.visibility = View.VISIBLE
                }
                is MainState.Error -> {
                    binding.mainContent.visibility = View.GONE
                    binding.error.visibility = View.VISIBLE
                    binding.error.text = state.message
                }
                is MainState.Loading -> {

                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
