package com.sevenpeakssoftware.patipan.shared.mapper

interface BaseCarsListItem {
  fun itemId(): Int?
}

data class CarsListItem(
  val id: Long? = null,
  val itemId: Int? = null,
  val title: String? = null,
  val image: String? = null,
  val publishDate: Long? = null,
  val ingress: String? = null
) : BaseCarsListItem {
  override fun itemId(): Int? = itemId
}

data class EmptyItem(val id: Int? = null) : BaseCarsListItem {
  override fun itemId(): Int?  = id
}


object CarsItemViewType {
  const val audi: Int = 0
  const val emptyView: Int = 1
}