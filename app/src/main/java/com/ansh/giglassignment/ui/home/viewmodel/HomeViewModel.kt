package com.ansh.giglassignment.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ansh.giglassignment.data.models.TimelineItem
import com.ansh.giglassignment.utils.NetworkResult
import com.ansh.giglassignment.utils.logEGlobal
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import org.json.JSONObject

class HomeViewModel : ViewModel() {

    private var fireStore: FirebaseFirestore = FirebaseFirestore.getInstance()

    // response Timeline
    private val _responseTimeline = MutableLiveData<NetworkResult<List<TimelineItem>>>()
    val responseTimeline: LiveData<NetworkResult<List<TimelineItem>>> get() = _responseTimeline


    init {
        getTimeline()
    }


    fun getTimeline() = viewModelScope.launch(Dispatchers.IO) {
        _responseTimeline.postValue(NetworkResult.Loading())
        try {

            val query = fireStore.collection("TimelineJsonList").get()
            val response = query.await()

            if (response != null && !response.isEmpty) {

                val listTimeline = ArrayList<TimelineItem>()
                val data = response.toObjects(Any::class.java)

                logEGlobal("SUCCESS in ${this@HomeViewModel.javaClass.simpleName} getTimeline() : ${data}")

                data.forEach { rawJsonString ->
                    val jsonBody = JSONObject(rawJsonString.toString())
                    val timelineJsonObject = jsonBody.optJSONObject("TimelineItem")
                    logEGlobal(timelineJsonObject.toString())
                    val timelineItem =
                        Gson().fromJson(timelineJsonObject.toString(), TimelineItem::class.java)
                    listTimeline.add(timelineItem!!)
                }
                _responseTimeline.postValue(NetworkResult.Success(listTimeline))
            } else if (response == null || response.isEmpty || query.exception!=null) {
                logEGlobal("ERROR in ${this@HomeViewModel.javaClass.simpleName} getTimeline() : ${query.exception?.message.toString()}")
                _responseTimeline.postValue(NetworkResult.Error(query.exception?.message))
            } else {
                logEGlobal("ERROR in ${this@HomeViewModel.javaClass.simpleName} getTimeline() : ${query.exception.toString()}")
                _responseTimeline.postValue(NetworkResult.Error("something went wrong"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            logEGlobal("ERROR in ${this@HomeViewModel.javaClass.simpleName} getTimeline() : ${e.message}")
            _responseTimeline.postValue(NetworkResult.Error("something went wrong EE"))
        }

    }

}