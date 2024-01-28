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
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var bindingHF: FragmentHomeBinding
    private val adapterTimeline by lazy { RvTimelineAdapter() }
    private val vmHome by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObservers()

    }

    private fun setRefreshing(isLoading:Boolean){
        if (isLoading){
            bindingHF.progressBar.visibility= View.VISIBLE
            bindingHF.loader.visibility= View.VISIBLE
            bindingHF.swipeRefreshLayout.isRefreshing=false
        }else{
            bindingHF.progressBar.visibility= View.INVISIBLE
            bindingHF.loader.visibility= View.GONE
            bindingHF.swipeRefreshLayout.isRefreshing=false
        }
    }

    private fun initObservers() {

        vmHome.responseTimeline.observe(viewLifecycleOwner) {

            when (it) {
                is NetworkResult.Error -> {
                    setRefreshing(false)
                    logEGlobal("error")
                    toastFragment(it.message.toString())


                }

                is NetworkResult.Loading -> {
                    logEGlobal("loading")

                    if (it.data!=null){
                        adapterTimeline.submitList(it.data)
                    }else{
                        toastFragment("error getting recommended list")
                    }
                    setRefreshing(true)

                }

                is NetworkResult.Success -> {
                    logEGlobal("success")

                    setRefreshing(false)
                    if (it.data!=null){
                        adapterTimeline.submitList(it.data)
                    }else{
                        toastFragment("error getting recommended list")
                    }
                }
            }

        }


    }

    private fun initView() {

        bindingHF.swipeRefreshLayout.setOnRefreshListener {

            //   binding.swipeRefreshLayout.isRefreshing=false
            vmHome.getTimelineData()

        }

        bindingHF.rvTimeline.adapter = adapterTimeline

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(inflater, container, false).also { bindingHF = it }.root
}