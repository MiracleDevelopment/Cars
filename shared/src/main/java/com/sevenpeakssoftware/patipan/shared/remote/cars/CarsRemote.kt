package com.sevenpeakssoftware.patipan.shared.remote.cars

import com.sevenpeakssoftware.patipan.model.room.RoomCarsItem
import com.sevenpeakssoftware.patipan.shared.mapper.BaseCarsListItem
import io.reactivex.Single

interface CarsRemote {
  fun getCarsList() : Single<ArrayList<BaseCarsListItem>>
}