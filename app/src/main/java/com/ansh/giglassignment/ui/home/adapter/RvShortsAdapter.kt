package com.ansh.giglassignment.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ansh.giglassignment.data.models.ShortsItem
import com.ansh.giglassignment.databinding.RvShortsItemLayoutBinding

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

    inner class ShortsViewHolder(binding: RvShortsItemLayoutBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(item: ShortsItem) {

        }
    }
}