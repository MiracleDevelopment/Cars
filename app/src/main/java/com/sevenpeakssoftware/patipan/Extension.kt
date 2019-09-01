package com.sevenpeakssoftware.patipan

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.*
import com.bumptech.glide.request.transition.Transition


fun <T : Fragment> AppCompatActivity.replaceFragment(
  container: Int,
  fragment: T,
  tagFragment: String,
  isBackStack: Boolean
) {
  val supportFragmentManager = supportFragmentManager.beginTransaction()

  if (isBackStack) {
    supportFragmentManager.replace(container, fragment, tagFragment).addToBackStack(null).commit()
  } else {
    supportFragmentManager.replace(container, fragment, tagFragment).commit()
  }

}

fun loadPhotoWithGlide(context: Context, url: String?, target: View) {
  if (target !is AppCompatImageView) return
  val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
  Glide.with(context).load(url).apply(requestOptions).into(target)
}