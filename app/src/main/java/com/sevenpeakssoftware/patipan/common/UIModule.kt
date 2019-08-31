package com.sevenpeakssoftware.patipan.common

import com.sevenpeakssoftware.patipan.view.adapter.CarsListAdapter
import org.koin.dsl.module

object UIModule {

  val carsListModule  = module {
    factory { CarsListAdapter() }
  }
}