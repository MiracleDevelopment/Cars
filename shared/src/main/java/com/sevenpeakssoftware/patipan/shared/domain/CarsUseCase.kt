package com.sevenpeakssoftware.patipan.shared.domain

import com.eggdigital.shared.domain.executor.PostExecutionThread
import com.sevenpeakssoftware.patipan.shared.base.ThreadExecutor
import com.sevenpeakssoftware.patipan.model.ListCarsResponse
import com.sevenpeakssoftware.patipan.shared.base.SingleUseCase
import com.sevenpeakssoftware.patipan.shared.data.CarsRepository
import io.reactivex.Single

class CarsUseCase(
  private val carsRepository: CarsRepository,
  threadExecutor: ThreadExecutor,
  postExecutor: PostExecutionThread
) : SingleUseCase<Unit, ListCarsResponse>(threadExecutor, postExecutor) {
  override fun buildUseCaseObservable(params: Unit): Single<ListCarsResponse> =  carsRepository.getListCars()
}