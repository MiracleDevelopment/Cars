package com.sevenpeakssoftware.patipan.view.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sevenpeakssoftware.patipan.common.SingleLiveEvent
import com.sevenpeakssoftware.patipan.shared.Result
import com.sevenpeakssoftware.patipan.shared.ResultFailed
import com.sevenpeakssoftware.patipan.shared.base.BaseDisposableSingle
import com.sevenpeakssoftware.patipan.shared.domain.CarsUseCase
import com.sevenpeakssoftware.patipan.shared.mapper.BaseCarsListItem

class CarsListScreenViewModel(
  private val carsUseCase: CarsUseCase
) : ViewModel() {
  private val listCarsResponse: ArrayList<BaseCarsListItem> = arrayListOf()

  private val mutableCarsResponse: MutableLiveData<ArrayList<BaseCarsListItem>> = MutableLiveData()

  private val singleCarsListLiveData: MutableLiveData<Result<ArrayList<BaseCarsListItem>>> =
    SingleLiveEvent()

  fun executeCarsList(isConnected: Boolean) {
    carsUseCase.execute(CarsDisposable(singleCarsListLiveData), isConnected)
  }

  fun setItemsCarsList(listCars: ArrayList<BaseCarsListItem>) {
    onClearList()
    listCarsResponse.addAll(listCars)
    mutableCarsResponse.value = listCarsResponse
  }

  private fun onClearList() {
    listCarsResponse.clear()
  }

  fun observeCarsList() = singleCarsListLiveData
  fun observeCarsResponse() = mutableCarsResponse

  class CarsDisposable(private val liveData: MutableLiveData<Result<ArrayList<BaseCarsListItem>>>) :
    BaseDisposableSingle<ArrayList<BaseCarsListItem>>() {
    override fun onSuccess(success: Result<ArrayList<BaseCarsListItem>>) {
      liveData.value = success
    }

    override fun onError(e: Throwable, error: Result<ArrayList<BaseCarsListItem>>) {
      liveData.value = ResultFailed(e)
    }
  }
}