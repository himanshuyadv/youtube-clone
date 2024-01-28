package com.ansh.giglassignment.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ansh.giglassignment.data.models.TimelineItem
import com.ansh.giglassignment.data.repository.TimelineRepository
import com.ansh.giglassignment.utils.NetworkResult
import com.ansh.giglassignment.utils.logEGlobal
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import org.json.JSONObject
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TimelineRepository
) : ViewModel() {
    // response Timeline
    val responseTimeline: LiveData<NetworkResult<List<TimelineItem>>> get() = repository.responseTimeline

    init {

        getTimelineData()
    }


    private var currentJob: Job? = null

     fun getTimelineData() {
        currentJob?.cancel() // Cancel the previous job if it's running

        currentJob = viewModelScope.launch(Dispatchers.IO) {
            repository.getTimelineData()
        }
    }


}