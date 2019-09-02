package com.sevenpeakssoftware.patipan.shared.cache

import androidx.room.*
import com.sevenpeakssoftware.patipan.model.room.RoomCarsItem
import io.reactivex.Single

@Dao
interface CarsItemsDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertCarsItem(carsItem: RoomCarsItem)

  @Update
  fun updateCarsItem(carsItem: RoomCarsItem)

  @Delete
  fun deleteCarsItem(carsItem: RoomCarsItem)

  @Query("SELECT * FROM CarsItems WHERE CarsItems.itemId = :id")
  fun getCarsItems(id: Int): RoomCarsItem?

  @Query("SELECT * FROM CarsItems")
  fun getAllCarsItem(): List<RoomCarsItem>

  @Query("SELECT * FROM CarsItems")
  fun getSingleAllCarsItem(): Single<List<RoomCarsItem>>
}