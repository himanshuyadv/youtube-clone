package com.ansh.giglassignment.data.models



data class TimelineItem(
    val id: String,
    val itemType: Int,
    val name: String,
    val shortsList: List<ShortsItem> = emptyList(),
    val postItem: PostItem? = null,
    val videoItem: VideoItem? = null
)

data class ShortsItem(
    val id: String,
    val thumbnailUrl: String? = null,
    val videoUrl: String? = null,
    val description: String? = null,
    val title: String? = null
)

data class PostItem(
    val id: String,
    val thumbnailUrl: String? = null,
    val imageUrl: String? = null,
    val description: String? = null,
    val title: String? = null
)

data class VideoItem(
    val id: String,
    val thumbnailUrl: String? = null,
    val videoUrl: String? = null,
    val description: String? = null,
    val title: String? = null
)
