package com.sevenpeakssoftware.patipan.shared.remote.cars

import com.sevenpeakssoftware.patipan.shared.cache.*
import com.sevenpeakssoftware.patipan.shared.mapper.BaseCarsListItem
import com.sevenpeakssoftware.patipan.shared.mapper.MapperCarsImpl
import com.sevenpeakssoftware.patipan.shared.remote.CarsService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CarsRemoteImpl(
  private val carsService: CarsService,
  private val carsDb: CarsItemsDao,
  private val carMapperImpl: MapperCarsImpl,
  private val roomMapper: RoomMapperImpl
) : CarsRemote {

  override fun getCarsList(): Single<ArrayList<BaseCarsListItem>> {
    return carsService.carsList().subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
      .flatMap { carsResponse ->
        carsResponse.content?.forEach {
          if (carsDb.getCarsItems(it.id ?:-1) == null){
             carsDb.insertCarsItem(roomMapper.mapperRoomCars(it))
          }else{
             carsDb.updateCarsItem(roomMapper.mapperRoomCars(it))
          }
        }
        return@flatMap Single.just(carMapperImpl.mapperCarsItem(ArrayList(carsDb.getAllCarsItem())))
      }
  }
}