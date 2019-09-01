package com.sevenpeakssoftware.patipan.view.mapper

interface MapperCars<in T,out M> {
  fun mapperCarsItem(items : T) : M
}