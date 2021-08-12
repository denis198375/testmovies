package com.testmovie.testmovie.Movies

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.testmovie.testmovie.R
import com.testmovie.testmovie.utils.Constants
import com.testmovie.testmovie.utils.GlideApp

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("imageUrl", "isBackdrop")
    fun bindImage(imageView: ImageView, imagePath: String, isBackdrop: Boolean) {
        val baseUrl: String
        baseUrl = if (isBackdrop) {
            Constants.BACKDROP_URL
        } else {
            Constants.IMAGE_URL
        }
        GlideApp.with(imageView.context)
            .load(baseUrl + imagePath)
            .placeholder(R.color.md_grey_200)
            .into(imageView)
    }



}