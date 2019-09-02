package com.sevenpeakssoftware.patipan.shared.remote

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceFactory {

  fun carsService(baseUrl: String, interceptor: ServiceInterceptors, stethoInterceptor: StethoInterceptor): CarsService {
    val okHttpClient = createOkHttpClient(interceptor, stethoInterceptor)
    return createRetrofit(baseUrl,okHttpClient, createGson()).create(CarsService::class.java)
  }

  private fun createRetrofit(baseUrl: String, okHttpClient: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder().baseUrl(baseUrl)
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create(gson))
      .client(okHttpClient)
      .build()
  }

  private fun createOkHttpClient(
    interceptor: Interceptor,
    stethoInterceptor: StethoInterceptor
  ): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(interceptor)
      .addInterceptor(stethoInterceptor)
      .build()
  }

  private fun createGson(): Gson {
    return GsonBuilder()
      .setLenient()
      .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
      .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
      .create()
  }
}