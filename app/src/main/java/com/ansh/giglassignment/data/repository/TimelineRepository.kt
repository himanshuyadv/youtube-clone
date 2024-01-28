package com.ansh.giglassignment.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ansh.giglassignment.data.models.TimelineItem
import com.ansh.giglassignment.data.room.room.GiglDatabase
import com.ansh.giglassignment.data.room.room.entities.timeline.TimelineEntity
import com.ansh.giglassignment.utils.NetworkResult
import com.ansh.giglassignment.utils.logEGlobal
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.coroutines.tasks.await
import org.json.JSONObject
import javax.inject.Inject

class TimelineRepository @Inject constructor(
    private val database: GiglDatabase,
    private val firebaseFirestore: FirebaseFirestore
) {

    // response Timeline
    private val _responseTimeline = MutableLiveData<NetworkResult<List<TimelineItem>>>()
    val responseTimeline: LiveData<NetworkResult<List<TimelineItem>>> get() = _responseTimeline

    suspend fun getTimelineData() {

        // setting data from database
        val listFromDatabase = database.getTimelineDao().getAllTimelineItems()

        val listToShow = mutableListOf<TimelineItem>()
        listFromDatabase.forEach {
            listToShow.add(timelineItemAsResponse(it))
        }
        _responseTimeline.postValue(NetworkResult.Loading(listToShow))




        try {

            val query = firebaseFirestore.collection("TimelineJsonList").get()
            val response = query.await()

            if (response != null && !response.isEmpty) {

                val listTimeline = ArrayList<TimelineItem>()
                val data = response.toObjects(Any::class.java)

                logEGlobal("SUCCESS in ${this@TimelineRepository.javaClass.simpleName} getTimeline() : ${data}")

                data.forEach { rawJsonString ->
                    val jsonBody = JSONObject(rawJsonString.toString())
                    val timelineJsonObject = jsonBody.optJSONObject("TimelineItem")
                    logEGlobal(timelineJsonObject.toString())
                    val timelineItem =
                        Gson().fromJson(timelineJsonObject.toString(), TimelineItem::class.java)
                    listTimeline.add(timelineItem!!)
                }
                // saving to database
                database.getTimelineDao().deleteAllTimelineItems()
                listTimeline.forEach {
                    database.getTimelineDao().insertTimelineItem(
                        timelineItemAsEntity(it)
                    )
                }
                _responseTimeline.postValue(NetworkResult.Success(listTimeline))
            } else if (response == null || response.isEmpty || query.exception != null) {
                logEGlobal("ERROR in ${this@TimelineRepository.javaClass.simpleName} getTimeline() : ${query.exception?.message.toString()}")
                _responseTimeline.postValue(NetworkResult.Error("Network Error"))
            } else {
                logEGlobal("ERROR in ${this@TimelineRepository.javaClass.simpleName} getTimeline() : ${query.exception.toString()}")
                _responseTimeline.postValue(NetworkResult.Error("something went wrong"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            logEGlobal("ERROR in ${this@TimelineRepository.javaClass.simpleName} getTimeline() : ${e.message}")
            _responseTimeline.postValue(NetworkResult.Error("something went wrong EE"))
        }


    }

    private fun timelineItemAsEntity(serverItem: TimelineItem): TimelineEntity {
        return TimelineEntity(
            id = serverItem.id,
            itemType = serverItem.itemType,
            name = serverItem.name,
            shortsList = serverItem.shortsList,
            postItem = serverItem.postItem,
            videoItem = serverItem.videoItem
        )
    }

    private fun timelineItemAsResponse(databaseItem: TimelineEntity): TimelineItem {
        return TimelineItem(
            id = databaseItem.id,
            itemType = databaseItem.itemType,
            name = databaseItem.name,
            shortsList = databaseItem.shortsList.orEmpty(),
            postItem = databaseItem.postItem,
            videoItem = databaseItem.videoItem
        )
    }

}