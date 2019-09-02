package com.sevenpeakssoftware.patipan.shared.domain

import android.util.Log
import com.eggdigital.shared.domain.executor.PostExecutionThread
import com.sevenpeakssoftware.patipan.shared.base.ThreadExecutor
import com.sevenpeakssoftware.patipan.shared.base.SingleUseCase
import com.sevenpeakssoftware.patipan.shared.data.CarsRepository
import com.sevenpeakssoftware.patipan.shared.mapper.BaseCarsListItem
import io.reactivex.Single

class CarsUseCase(
  private val carsRepository: CarsRepository,
  threadExecutor: ThreadExecutor,
  postExecutor: PostExecutionThread
) : SingleUseCase<Boolean, ArrayList<BaseCarsListItem>>(threadExecutor, postExecutor) {

  override fun buildUseCaseObservable(params: Boolean): Single<ArrayList<BaseCarsListItem>> {
    Log.d("getCarsList", "active")
    return carsRepository.getListCars(params)
  }
}