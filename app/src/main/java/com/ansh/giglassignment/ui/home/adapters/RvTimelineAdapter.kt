package com.ansh.giglassignment.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ansh.giglassignment.R
import com.ansh.giglassignment.data.models.TimelineItem
import com.ansh.giglassignment.data.models.VideoItem
import com.ansh.giglassignment.databinding.RvTimelinePostItemLayoutBinding
import com.ansh.giglassignment.databinding.RvTimelineShortsItemLayoutBinding
import com.ansh.giglassignment.databinding.RvTimelineVideoItemLayoutBinding
import com.ansh.giglassignment.utils.UrlUtils.openYouTubeLink
import com.bumptech.glide.Glide


class RvTimelineAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listTimelineData = mutableListOf<TimelineItem>()

    companion object {
        const val ITEM_TYPE_VIDEO = 1
        const val ITEM_TYPE_POST = 2
        const val ITEM_TYPE_SHORTS = 3
    }

    fun submitList(list: List<TimelineItem>){
        listTimelineData.clear()
        listTimelineData.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val li = LayoutInflater.from(parent.context)
        when (viewType) {
            ITEM_TYPE_POST -> {
                val binding = RvTimelinePostItemLayoutBinding.inflate(li, parent, false)
                return ViewHolderPost(binding)
            }

            ITEM_TYPE_VIDEO -> {
                val binding = RvTimelineVideoItemLayoutBinding.inflate(li, parent, false)
                return ViewHolderVideo(binding)
            }

            ITEM_TYPE_SHORTS -> {
                val binding = RvTimelineShortsItemLayoutBinding.inflate(li, parent, false)
                return ViewHolderShorts(binding)
            }

            else -> {
                val binding = RvTimelinePostItemLayoutBinding.inflate(li, parent, false)
                return ViewHolderPost(binding)
            }
        }

    }

    override fun getItemCount() = listTimelineData.size

    override fun getItemViewType(position: Int): Int {
        return listTimelineData[position].itemType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ITEM_TYPE_POST -> (holder as ViewHolderPost).bind(listTimelineData[position])
            ITEM_TYPE_VIDEO -> (holder as ViewHolderVideo).bind(listTimelineData[position])
            ITEM_TYPE_SHORTS -> (holder as ViewHolderShorts).bind(listTimelineData[position])
        }
    }

    inner class ViewHolderPost(val binding: RvTimelinePostItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TimelineItem) {
            binding.tvTitle.text=item.postItem?.title
            binding.tvDescription.text=item.postItem?.description
            Glide.with(binding.root).load(item.postItem?.thumbnailUrl.toString()).placeholder(R.drawable.ic_youtube).into(binding.ivThumbnail)
            Glide.with(binding.root).load(item.postItem?.thumbnailUrl.toString()).placeholder(R.drawable.ic_youtube).into(binding.SIVProfile)

        }

    }

    inner class ViewHolderShorts(val binding: RvTimelineShortsItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val adapterShorts=RvShortsAdapter()

        fun bind(item: TimelineItem) {
            binding.rvShorts.adapter=adapterShorts
            adapterShorts.submitList(item.shortsList)
        }

    }

    inner class ViewHolderVideo(val binding: RvTimelineVideoItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TimelineItem) {

            binding.tvTitle.text=item.videoItem?.title
            binding.tvDescription.text=item.videoItem?.description
            Glide.with(binding.root).load(item.videoItem?.thumbnailUrl.toString()).placeholder(R.drawable.ic_youtube).into(binding.ivThumbnail)
            Glide.with(binding.root).load(item.videoItem?.thumbnailUrl.toString()).placeholder(R.drawable.ic_youtube).into(binding.SIVProfile)

            itemView.setOnClickListener {
                openYouTubeLink(item.videoItem?.videoUrl.toString(),itemView.context)
            }

        }

    }
}