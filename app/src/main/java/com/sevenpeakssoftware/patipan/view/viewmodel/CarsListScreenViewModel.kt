package com.sevenpeakssoftware.patipan.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sevenpeakssoftware.patipan.model.ListCarsResponse
import com.sevenpeakssoftware.patipan.shared.Result
import com.sevenpeakssoftware.patipan.shared.base.BaseDisposableSingle
import com.sevenpeakssoftware.patipan.shared.domain.CarsUseCase
import com.sevenpeakssoftware.patipan.view.adapter.BaseCarsListItem
import com.sevenpeakssoftware.patipan.view.mapper.MapperCarsImpl

class CarsListScreenViewModel(
  private val carsUseCase: CarsUseCase,
  private val mapperCars: MapperCarsImpl
) : ViewModel() {
  private val listCarsResponse: ArrayList<BaseCarsListItem> = arrayListOf()

  private val mutableCarsResponse: MutableLiveData<ArrayList<BaseCarsListItem>> = MutableLiveData()

  private val mutableCarsListLiveData: MutableLiveData<Result<ListCarsResponse>> = MutableLiveData()

  fun executeCarsList() {
    carsUseCase.execute(CarsDisposable(mutableCarsListLiveData), Unit)
  }

  fun setItemsCarsList(listCars: ListCarsResponse) {
    listCarsResponse.addAll(mapperCars.mapperCarsItem(listCars.content ?: return))

    mutableCarsResponse.value = listCarsResponse
  }

  fun observeCarsList() = mutableCarsListLiveData
  fun observeCarsResponse() = mutableCarsResponse


  inner class CarsDisposable(private val liveData: MutableLiveData<Result<ListCarsResponse>>) :
    BaseDisposableSingle<ListCarsResponse>() {
    override fun onSuccess(success: Result<ListCarsResponse>) {
      liveData.value = success
    }

    override fun onError(
      e: Throwable,
      error: Result<ListCarsResponse>
    ) {
      liveData.value = error
    }
  }
}