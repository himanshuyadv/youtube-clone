package com.ansh.giglassignment.data.room.room.type_convertors

import androidx.room.TypeConverter
import com.ansh.giglassignment.data.models.PostItem
import com.ansh.giglassignment.data.models.ShortsItem
import com.ansh.giglassignment.data.models.VideoItem
import com.google.gson.Gson


class MyTypeConverters {

    @TypeConverter
    fun fromListToJSON(list: List<String>): String {
        return Gson().toJson(list)
    }


    @TypeConverter
    fun fromJSONToList(json: String): List<String> {
        return Gson().fromJson(json, Array<String>::class.java).toList()
    }

    @TypeConverter
    fun fromJSONToShortsList(json: String): List<ShortsItem> {
        return Gson().fromJson(json, Array<ShortsItem>::class.java).toList()
    }

    @TypeConverter
    fun fromShortsListToJSON(list: List<ShortsItem>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromJSONToPostItem(json: String): PostItem? {
        return Gson().fromJson(json, PostItem::class.java)
    }

    @TypeConverter
    fun fromPostItemToJSON(item: PostItem?): String {
        return Gson().toJson(item)
    }

    @TypeConverter
    fun fromJSONToVideoItem(json: String): VideoItem? {
        return Gson().fromJson(json, VideoItem::class.java)
    }

    @TypeConverter
    fun fromVideoItemToJSON(item: VideoItem?): String {
        return Gson().toJson(item)
    }
}