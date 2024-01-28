package com.ansh.giglassignment.utils

sealed class NetworkResult<T>(val data:T?=null, val message:String?=null, val messageList:HashMap<String,String>?=null ) {
    class Success<T>(data: T):NetworkResult<T>(data)
    class Error<T>(message:String?,data: T?=null,messageList: HashMap<String, String>?=null):NetworkResult<T>(data,message,messageList)
    class Loading<T>(offlineData: T?=null):NetworkResult<T>(offlineData)
}