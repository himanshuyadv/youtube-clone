package com.ansh.giglassignment.utils

import java.util.Locale
import java.util.regex.Matcher
import java.util.regex.Pattern

object UrlUtils {
    fun getYoutubeThumbnailUrlFromVideoUrl(videoUrl: String): String? {
        return "http://img.youtube.com/vi/" + getYouTubeShortsThumbnailUrl(videoUrl) + "/0.jpg"
    }

    fun getYoutubeVideoIdFromUrl(inUrl: String): String? {
        var inUrl = inUrl
        inUrl = inUrl.replace("&feature=youtu.be", "")
        if (inUrl.lowercase(Locale.getDefault()).contains("youtu.be")) {
            return inUrl.substring(inUrl.lastIndexOf("/") + 1)
        }
        val pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*"
        val compiledPattern: Pattern = Pattern.compile(pattern)
        val matcher: Matcher = compiledPattern.matcher(inUrl)
        return if (matcher.find()) {
            matcher.group()
        } else null
    }

    fun getYouTubeShortsThumbnailUrl(shortsUrl: String): String? {
        val videoId = extractVideoId(shortsUrl)
        return if (videoId != null) {
            "https://i.ytimg.com/vi/$videoId/default.jpg"
        } else {
            null
        }
    }

    fun extractVideoId(shortsUrl: String): String? {
        val regex = Regex("/shorts/(.*?)(\\?|\$)")
        val matchResult = regex.find(shortsUrl)
        return matchResult?.groups?.get(1)?.value
    }
}