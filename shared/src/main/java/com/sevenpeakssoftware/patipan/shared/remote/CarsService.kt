package com.sevenpeakssoftware.patipan.shared.remote

import com.sevenpeakssoftware.patipan.model.ListCarsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface CarsService {

  @GET("article/get_articles_list")
  fun carsList(): Single<ListCarsResponse>
}