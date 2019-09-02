package com.sevenpeakssoftware.patipan.common

import androidx.room.Room
import com.eggdigital.shared.domain.executor.PostExecutionThread
import com.sevenpeakssoftware.patipan.shared.base.ThreadExecutor
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.sevenpeakssoftware.patipan.shared.BuildConfig
import com.sevenpeakssoftware.patipan.shared.cache.*
import com.sevenpeakssoftware.patipan.shared.data.CarsRepository
import com.sevenpeakssoftware.patipan.shared.data.CarsRepositoryImpl
import com.sevenpeakssoftware.patipan.shared.data.JobExecutor
import com.sevenpeakssoftware.patipan.shared.data.UiThread
import com.sevenpeakssoftware.patipan.shared.domain.CarsUseCase
import com.sevenpeakssoftware.patipan.shared.remote.ServiceFactory
import com.sevenpeakssoftware.patipan.shared.remote.ServiceInterceptors
import com.sevenpeakssoftware.patipan.shared.remote.cars.CarsRemote
import com.sevenpeakssoftware.patipan.shared.remote.cars.CarsRemoteImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

object ServiceModule {

  private const val nameDb : String = "cars_db"

  val threadModule = module {
    factory { JobExecutor() } bind ThreadExecutor::class
    factory { UiThread() } bind PostExecutionThread::class
  }

  val useCase = module {
    factory { CarsUseCase(get(), get(), get()) }
  }

  val interceptorModule = module {
    single { StethoInterceptor() }
    single { ServiceInterceptors() }
  }

  val serviceModule = module {
    single { ServiceFactory.carsService(BuildConfig.BASE_URL, get(), get()) }
  }

  val remoteModule = module {
    factory { Room.databaseBuilder(androidContext(),AppRoomDatabase::class.java, nameDb).build() }

    factory { RoomMapperImpl() } bind RoomMapper::class
    factory { CarsRemoteImpl(get(),get<AppRoomDatabase>().carsItemDao(),get(),get()) } bind CarsRemote::class
  }

  val repositoryModule = module {
    factory{  CarsRepositoryImpl(get(),get<AppRoomDatabase>().carsItemDao(),get()) } bind CarsRepository::class
  }


}