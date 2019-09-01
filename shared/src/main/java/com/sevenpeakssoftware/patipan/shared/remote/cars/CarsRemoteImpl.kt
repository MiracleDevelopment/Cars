package com.sevenpeakssoftware.patipan.shared.remote.cars

import com.sevenpeakssoftware.patipan.model.ListCarsResponse
import com.sevenpeakssoftware.patipan.shared.remote.CarsService
import io.reactivex.Single

class CarsRemoteImpl(private val carsService: CarsService) : CarsRemote{
  override fun getCarsList(): Single<ListCarsResponse>  = carsService.carsList()
}