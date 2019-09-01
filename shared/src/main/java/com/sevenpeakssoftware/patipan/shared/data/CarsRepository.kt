package com.sevenpeakssoftware.patipan.shared.data

import com.sevenpeakssoftware.patipan.model.ListCarsResponse
import io.reactivex.Single

interface CarsRepository {
  fun getListCars() : Single<ListCarsResponse>
}