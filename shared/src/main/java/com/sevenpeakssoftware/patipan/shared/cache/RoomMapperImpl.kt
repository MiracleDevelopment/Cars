package com.sevenpeakssoftware.patipan.shared.cache

import com.sevenpeakssoftware.patipan.model.ListCarsContent
import com.sevenpeakssoftware.patipan.model.room.RoomCarsItem

class RoomMapperImpl : RoomMapper<ListCarsContent, RoomCarsItem> {
  override fun mapperRoomCars(item: ListCarsContent): RoomCarsItem {
    return RoomCarsItem(itemId = item.id,title = item.title,image = item.image,publishDate = item.created,ingress = item.ingress)
  }
}