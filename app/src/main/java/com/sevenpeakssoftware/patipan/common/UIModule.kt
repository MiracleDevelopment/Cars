package com.sevenpeakssoftware.patipan.common


import com.sevenpeakssoftware.patipan.view.adapter.CarsListAdapter
import com.sevenpeakssoftware.patipan.shared.mapper.MapperCars
import com.sevenpeakssoftware.patipan.shared.mapper.MapperCarsImpl
import com.sevenpeakssoftware.patipan.view.viewmodel.CarsListScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

object UIModule {

  val carsListModule = module {
    factory { MapperCarsImpl() } bind MapperCars::class
    factory { CarsListAdapter() }
    viewModel { CarsListScreenViewModel(get()) }
  }
}