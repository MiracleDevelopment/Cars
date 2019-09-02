package com.sevenpeakssoftware.patipan.shared.cache

interface RoomMapper<in T,out M> {
  fun mapperRoomCars(item : T) : M
}