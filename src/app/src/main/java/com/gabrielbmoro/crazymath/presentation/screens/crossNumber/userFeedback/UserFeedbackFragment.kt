package com.gabrielbmoro.crazymath.presentation.screens.crossNumber.userFeedback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gabrielbmoro.crazymath.R
import com.gabrielbmoro.crazymath.databinding.FragmentUserFeedbackBinding
import com.gabrielbmoro.crazymath.presentation.extensions.changeStateFrom
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFeedbackFragment : Fragment() {

    private lateinit var binding: FragmentUserFeedbackBinding
    private val viewModel: UserFeedbackViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUserFeedbackBinding.inflate(inflater, container, false)

        viewModel.liveDataLoading.observe(viewLifecycleOwner) {
            binding.progressBar.changeStateFrom(it)
        }

        viewModel.sendUserFeedbackResult.observe(viewLifecycleOwner) { event ->
            val result = event.peekContent()
            if (result) {
                Toast.makeText(context, R.string.thanks_for_your_feedback, Toast.LENGTH_SHORT)
                    .show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(context, R.string.try_again_later, Toast.LENGTH_SHORT).show()
            }
        }

        binding.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navoTalk.setup(getString(R.string.user_feedback_text_hint))

        binding.buttonSend.setOnClickListener {
            viewModel.onSendUserFeedback(binding.etUserFeedback.text.toString())
        }
    }
}