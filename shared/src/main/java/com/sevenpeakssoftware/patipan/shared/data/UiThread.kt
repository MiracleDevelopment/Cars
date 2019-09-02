package com.sevenpeakssoftware.patipan.shared.data

import com.eggdigital.shared.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * MainThread (UI Thread) implementation based on a [Scheduler]
 * which will fatchCategory actions on the Android UI thread
 */

class UiThread : PostExecutionThread {

  override val scheduler: Scheduler
    get() = AndroidSchedulers.mainThread()

}