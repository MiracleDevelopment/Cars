package com.sevenpeakssoftware.patipan.shared.data

import com.sevenpeakssoftware.patipan.shared.cache.CarsItemsDao
import com.sevenpeakssoftware.patipan.shared.mapper.BaseCarsListItem
import com.sevenpeakssoftware.patipan.shared.mapper.MapperCarsImpl
import com.sevenpeakssoftware.patipan.shared.remote.cars.CarsRemote
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CarsRepositoryImpl(
  private val remote: CarsRemote,
  private val carsDb: CarsItemsDao,
  private val carMapperImpl: MapperCarsImpl
) :
  CarsRepository {
  override fun getListCars(isConnected: Boolean): Single<ArrayList<BaseCarsListItem>> {
    return if (isConnected) {
      remote.getCarsList()
    } else {
      carsDb.getSingleAllCarsItem().subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).flatMap {
        return@flatMap Single.just(carMapperImpl.mapperCarsItem(ArrayList(it)))
      }
    }
  }
}