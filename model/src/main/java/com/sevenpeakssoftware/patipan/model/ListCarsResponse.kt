package com.sevenpeakssoftware.patipan.model

import com.google.gson.annotations.SerializedName

data class ListCarsResponse(
  @SerializedName("status") val status: String? = null
  , @SerializedName("content") val content: ArrayList<ListCarsContent>? = null
  , @SerializedName("serverTime") val serverTime: String? = null
)

data class ListCarsContent(
  @SerializedName("id") val id: Int? = null
  , @SerializedName("title") val title: String? = null
  , @SerializedName("dateTime") val dateTime: String? = null
  , @SerializedName("tag") val listTag: ArrayList<String>? = null
  , @SerializedName("content") val content: ArrayList<CarsContent>? = null
  , @SerializedName("ingress") val ingress: String? = null
  , @SerializedName("image") val image: String? = null
  , @SerializedName("created") val created: Long? = null
  , @SerializedName("changed") val changed: Long? = null
)

data class CarsContent(
  @SerializedName("type") val type: String? = null
  , @SerializedName("subject") val subject: String? = null
  , @SerializedName("description") val description: String? = null
)