package com.ansh.giglassignment.data.models

data class TimelineItem(
    val id: String,
    val itemType: Int,
    val name: String,
    val shortsList: List<ShortsItem> = emptyList(),
    val postItem:PostItem?=null,
    val videoItem:VideoItem?=null
)

data class ShortsItem(val id: String)
data class PostItem(val id: String)
data class VideoItem(val id: String)
