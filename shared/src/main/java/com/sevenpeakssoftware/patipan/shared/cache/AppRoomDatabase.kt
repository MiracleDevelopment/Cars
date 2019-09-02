package com.sevenpeakssoftware.patipan.shared.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sevenpeakssoftware.patipan.model.room.RoomCarsItem

@Database(entities = [RoomCarsItem::class],version = 1)
abstract class AppRoomDatabase : RoomDatabase() {
  abstract fun carsItemDao() : CarsItemsDao
}