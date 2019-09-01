package com.sevenpeakssoftware.patipan.shared.data

import com.sevenpeakssoftware.patipan.model.ListCarsResponse
import com.sevenpeakssoftware.patipan.shared.remote.cars.CarsRemote
import io.reactivex.Single

class CarsRepositoryImpl(private val remote : CarsRemote) : CarsRepository {
  override fun getListCars(): Single<ListCarsResponse>  = remote.getCarsList()
}