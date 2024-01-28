package com.ansh.giglassignment.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ansh.giglassignment.R
import com.ansh.giglassignment.data.models.ShortsItem
import com.ansh.giglassignment.databinding.RvShortsItemLayoutBinding
import com.bumptech.glide.Glide
import java.util.Locale
import java.util.regex.Matcher
import java.util.regex.Pattern

class RvShortsAdapter : RecyclerView.Adapter<RvShortsAdapter.ShortsViewHolder>() {

    private val listShorts = mutableListOf<ShortsItem>()

    fun submitList(list: List<ShortsItem>) {
        listShorts.clear()
        listShorts.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RvShortsAdapter.ShortsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvShortsItemLayoutBinding.inflate(inflater, parent, false)
        return ShortsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RvShortsAdapter.ShortsViewHolder, position: Int) {
        holder.bind(listShorts[position])
    }

    override fun getItemCount() = listShorts.size

    inner class ShortsViewHolder(val binding: RvShortsItemLayoutBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(item: ShortsItem) {
            Glide.with(binding.root).load(item.thumbnailUrl.toString()).placeholder(R.drawable.ic_youtube).into(binding.vThumbnail)
        }
    }

}
