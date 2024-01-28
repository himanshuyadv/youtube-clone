package com.ansh.giglassignment.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ansh.giglassignment.data.models.PostItem
import com.ansh.giglassignment.data.models.ShortsItem
import com.ansh.giglassignment.data.models.TimelineItem
import com.ansh.giglassignment.data.models.VideoItem
import com.ansh.giglassignment.databinding.FragmentHomeBinding
import com.ansh.giglassignment.ui.home.adapter.RvTimelineAdapter


class HomeFragment : Fragment() {

    private lateinit var bindingHF:FragmentHomeBinding
    private val adapterTimeline by lazy { RvTimelineAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObservers()

    }

    private fun initObservers() {

        val timelineItemList= mutableListOf<TimelineItem>()
        val shortsItemList= mutableListOf<ShortsItem>()
        shortsItemList.add(ShortsItem(""))
        shortsItemList.add(ShortsItem(""))
        shortsItemList.add(ShortsItem(""))
        shortsItemList.add(ShortsItem(""))
        shortsItemList.add(ShortsItem(""))

        val postItem =PostItem("")
        val videoItem=VideoItem("")
        timelineItemList.add(TimelineItem("",RvTimelineAdapter.ITEM_TYPE_POST, "", emptyList(),postItem,null))
        timelineItemList.add(TimelineItem("",RvTimelineAdapter.ITEM_TYPE_SHORTS,"" , shortsItemList,null,null))
        timelineItemList.add(TimelineItem("",RvTimelineAdapter.ITEM_TYPE_VIDEO, "", emptyList(),null,videoItem))
        timelineItemList.add(TimelineItem("",RvTimelineAdapter.ITEM_TYPE_POST, "", emptyList(),postItem,null))
        adapterTimeline.submitList(timelineItemList)

    }

    private fun initView() {
       bindingHF.rvTimeline.adapter=adapterTimeline

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    )=FragmentHomeBinding.inflate(inflater,container,false).also { bindingHF=it }.root
}