package com.sevenpeakssoftware.patipan.shared.data

import com.sevenpeakssoftware.patipan.shared.mapper.BaseCarsListItem
import io.reactivex.Single

interface CarsRepository {
  fun getListCars(isConnected : Boolean): Single<ArrayList<BaseCarsListItem>>
}