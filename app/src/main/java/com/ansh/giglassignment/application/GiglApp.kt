package com.ansh.giglassignment.application

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GiglApp : Application() {

    override fun onCreate() {


        with(AppController) {
            // initialising AppController
            appContext = this@GiglApp
            viewModelApp = ViewModelProvider.AndroidViewModelFactory(this@GiglApp)
                .create(ViewModelApp::class.java)
        }

        super.onCreate()
    }
}