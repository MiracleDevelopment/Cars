package com.sevenpeakssoftware.patipan.shared.mapper

interface MapperCars<in T,out M> {
  fun mapperCarsItem(items : T) : M
}