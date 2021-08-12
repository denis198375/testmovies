package com.testmovie.testmovie

import com.testmovie.testmovie.Movies.MoviesFragment.Companion.newInstance
import com.testmovie.testmovie.utils.ActivityUtils.replaceFragmentInActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        if (savedInstanceState == null) {
            setupViewFragment()
        }
    }

    private fun setupViewFragment() {
        val discoverMoviesFragment = newInstance()
        replaceFragmentInActivity(
            supportFragmentManager, discoverMoviesFragment, R.id.fragment_container
        )
    }
}