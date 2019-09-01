package com.sevenpeakssoftware.patipan.view.mapper

import com.sevenpeakssoftware.patipan.model.ListCarsContent
import com.sevenpeakssoftware.patipan.view.adapter.BaseCarsListItem
import com.sevenpeakssoftware.patipan.view.adapter.CarsListItem
import java.util.*
import kotlin.collections.ArrayList

class MapperCarsImpl : MapperCars<ArrayList<ListCarsContent>, ArrayList<BaseCarsListItem>> {
  private val listCarsItem: ArrayList<BaseCarsListItem> = arrayListOf()

  override fun mapperCarsItem(items: ArrayList<ListCarsContent>): ArrayList<BaseCarsListItem> {
    items.forEachIndexed { index, item ->
      val carsItems = CarsListItem(
        Calendar.getInstance().timeInMillis + index,
        item.id,
        item.title,
        item.image,
        item.created,
        item.ingress
      )

      listCarsItem.add(carsItems)
    }

    return listCarsItem
  }
}