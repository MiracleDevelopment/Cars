package com.sevenpeakssoftware.patipan.shared.remote.cars

import com.sevenpeakssoftware.patipan.model.ListCarsResponse
import io.reactivex.Single

interface CarsRemote {
  fun getCarsList() : Single<ListCarsResponse>
}