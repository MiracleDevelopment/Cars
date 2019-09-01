package com.sevenpeakssoftware.patipan

import android.app.Application
import com.sevenpeakssoftware.patipan.common.UIModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module


class CarsApplication : Application() {
  private val listModules: ArrayList<Module> = arrayListOf(UIModule.carsListModule)

  override fun onCreate() {
    super.onCreate()
    setUpKoin()
  }

  private fun setUpKoin() {
    startKoin {
      androidLogger(Level.DEBUG)
      modules(listModules)
    }
  }
}