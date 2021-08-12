package com.testmovie.testmovie

import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageView = findViewById<ImageView>(R.id.image_loading)
//        Glide.with(baseContext).load(R.raw.marvel).into(imageView)
//        startLoading()
    }

    private fun startLoading() {
        val handler = Handler()
        handler.postDelayed({ finish() }, 4600)
    }


    }
