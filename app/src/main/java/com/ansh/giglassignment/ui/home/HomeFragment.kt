package com.ansh.giglassignment.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ansh.giglassignment.databinding.FragmentHomeBinding
import com.ansh.giglassignment.ui.home.adapters.RvTimelineAdapter
import com.ansh.giglassignment.ui.home.viewmodel.HomeViewModel
import com.ansh.giglassignment.utils.NetworkResult
import com.ansh.giglassignment.utils.logEGlobal
import com.ansh.giglassignment.utils.toastFragment


class HomeFragment : Fragment() {

    private lateinit var bindingHF: FragmentHomeBinding
    private val adapterTimeline by lazy { RvTimelineAdapter() }
    private val vmHome by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObservers()

    }

    private fun initObservers() {

        vmHome.responseTimeline.observe(viewLifecycleOwner) {

            when (it) {
                is NetworkResult.Error -> {
                    logEGlobal("error")
                    toastFragment(it.message.toString())

                }

                is NetworkResult.Loading -> {
                    logEGlobal("loading")

                }

                is NetworkResult.Success -> {
                    logEGlobal("success")
                    adapterTimeline.submitList(it.data!!)
                }
            }

        }


    }

    private fun initView() {
        bindingHF.rvTimeline.adapter = adapterTimeline

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(inflater, container, false).also { bindingHF = it }.root
}