package com.ansh.giglassignment.data.room.room.entities.timeline

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ansh.giglassignment.data.models.PostItem
import com.ansh.giglassignment.data.models.ShortsItem
import com.ansh.giglassignment.data.models.VideoItem

@Entity("timeline_list")
data class TimelineEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val itemType: Int,
    val name: String,
    val shortsList: List<ShortsItem>?=null,
    val postItem: PostItem? = null,
    val videoItem: VideoItem? = null
)


