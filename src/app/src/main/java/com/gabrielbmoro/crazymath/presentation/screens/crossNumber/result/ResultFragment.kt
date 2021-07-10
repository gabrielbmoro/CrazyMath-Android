package com.gabrielbmoro.crazymath.presentation.screens.crossNumber.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.gabrielbmoro.crazymath.R
import com.gabrielbmoro.crazymath.databinding.FragmentResultBinding
import com.gabrielbmoro.crazymath.presentation.extensions.changeStateFrom
import org.koin.core.parameter.parametersOf

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private val args: ResultFragmentArgs by navArgs()
    private val viewModel: ResultViewModel by viewModel {
        parametersOf(
                args.points,
                args.wasSendToRank
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)

        viewModel.liveDataLoading.observe(viewLifecycleOwner, {
            binding.progressBar.changeStateFrom(it)
        })

        viewModel.rankPosition.observe(viewLifecycleOwner, {
            binding.tvRankPosition.text = getString(R.string.rank_position, it.peekContent())
        })

        binding.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonOk.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.tvTotalPoints.text = getString(R.string.points, viewModel.points)
    }
}