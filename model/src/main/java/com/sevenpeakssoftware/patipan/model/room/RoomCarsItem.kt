package com.sevenpeakssoftware.patipan.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CarsItems")
data class RoomCarsItem(
  @PrimaryKey(autoGenerate = true) val id: Long? = null,
  val itemId: Int? = null,
  val title: String? = null,
  val image: String? = null,
  val publishDate: Long? = null,
  val ingress: String? = null
)