package com.sevenpeakssoftware.patipan

import android.content.Context
import android.nfc.FormatException
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


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

fun getDateFormat(timeMillion: Long?): String? {
  val currentCalendar: Calendar = Calendar.getInstance()
  val calendar: Calendar = Calendar.getInstance()

  timeMillion?.let {
    calendar.timeInMillis = timeMillion.toLong()
  }

  val patternFormat =
    if (currentCalendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) "dd MMMM,HH:mm" else "dd MMMM YYYY,HH:mm"
  val simpleDateFormat = SimpleDateFormat(patternFormat, Locale.getDefault())
  return try {
    simpleDateFormat.format(calendar.time)
  } catch (e: ParseException) {
    e.printStackTrace()
    null
  }
}