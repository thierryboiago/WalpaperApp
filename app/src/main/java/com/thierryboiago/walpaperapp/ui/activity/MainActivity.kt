package com.thierryboiago.walpaperapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thierryboiago.walpaperapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}