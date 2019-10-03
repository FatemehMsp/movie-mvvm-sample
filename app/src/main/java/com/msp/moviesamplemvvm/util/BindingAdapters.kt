package com.msp.moviesamplemvvm.util

import android.content.res.ColorStateList
import android.text.TextUtils
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.msp.moviesamplemvvm.R

object BindingAdapters {

    @BindingAdapter("app:srcCompat")
    @JvmStatic
    fun setPosterFromUrl(image: ImageView, imagePath: String) {
        if (!TextUtils.isEmpty(imagePath)) {
            try {
                Glide.with(image.context).load(imagePath)
                    .bitmapTransform(FitCenter(image.context))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(image)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else
            image.setImageDrawable(
                ContextCompat.getDrawable(
                    image.context,
                    R.drawable.ic_image_black_24dp
                )
            )
    }

}