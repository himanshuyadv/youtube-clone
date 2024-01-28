package com.ansh.giglassignment.utils

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun Any.logEGlobal(message: String,tag: String?=null) {

    if (tag != null) {
        Log.e(tag, message)
    } else {
        Log.e(this::class.java.simpleName, message)
    }
}

fun Activity.toastActivity(message: String, tag: String? = "Default Tag") =
    CoroutineScope(Dispatchers.Main).launch {
        logEGlobal("TOAST MESSAGE : $tag - $message")
        Toast.makeText(this@toastActivity, message, Toast.LENGTH_SHORT).show()
    }

fun Fragment.toastFragment(message: String, tag: String? = "Default Tag") =
    this@toastFragment.lifecycleScope.launch(Dispatchers.Main) {
        logEGlobal("TOAST MESSAGE : $tag - $message")
        Toast.makeText(this@toastFragment.requireContext(), message, Toast.LENGTH_SHORT).show()


    }