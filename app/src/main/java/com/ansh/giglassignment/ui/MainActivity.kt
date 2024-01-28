package com.ansh.giglassignment.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ansh.giglassignment.databinding.ActivityMainBinding
import com.ansh.giglassignment.utils.BaseActivity

class MainActivity : BaseActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}