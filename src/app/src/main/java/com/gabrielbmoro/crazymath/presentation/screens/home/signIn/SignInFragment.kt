package com.gabrielbmoro.crazymath.presentation.screens.home.signIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.gabrielbmoro.crazymath.R
import com.gabrielbmoro.crazymath.databinding.FragmentSignInBinding
import com.gabrielbmoro.crazymath.presentation.extensions.changeStateFrom
import com.gabrielbmoro.crazymath.presentation.extensions.transformToBeAnEmailField

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private val viewModel: SignInViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        viewModel.liveDataLoading.observe(viewLifecycleOwner) {
            binding.progressBar.changeStateFrom(it)
            val isNotLoading = !it.peekContent()
            binding.saveButton.isEnabled = isNotLoading
            binding.cancelButton.isEnabled = isNotLoading
        }

        viewModel.liveDataOnSuccessEvent.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

        binding.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.emailInputLayout.transformToBeAnEmailField()

        binding.navoTalk.setup(getString(R.string.please_type_your_email))

        binding.cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.saveButton.setOnClickListener {
            viewModel.signIn(binding.emailInput.text.toString())
        }
    }
}