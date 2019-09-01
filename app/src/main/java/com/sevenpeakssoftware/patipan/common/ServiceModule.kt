package com.sevenpeakssoftware.patipan.common

import com.eggdigital.shared.domain.executor.PostExecutionThread
import com.sevenpeakssoftware.patipan.shared.base.ThreadExecutor
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.sevenpeakssoftware.patipan.shared.data.CarsRepository
import com.sevenpeakssoftware.patipan.shared.data.CarsRepositoryImpl
import com.sevenpeakssoftware.patipan.shared.data.JobExecutor
import com.sevenpeakssoftware.patipan.shared.data.UiThread
import com.sevenpeakssoftware.patipan.shared.domain.CarsUseCase
import com.sevenpeakssoftware.patipan.shared.remote.ServiceFactory
import com.sevenpeakssoftware.patipan.shared.remote.ServiceInterceptors
import com.sevenpeakssoftware.patipan.shared.remote.cars.CarsRemote
import com.sevenpeakssoftware.patipan.shared.remote.cars.CarsRemoteImpl
import org.koin.dsl.bind
import org.koin.dsl.module

object ServiceModule {

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
    factory { ServiceFactory.carsService("https://www.apphusetreach.no/application/119267/", get(), get()) }
  }

  val remoteModule = module {
    factory { CarsRemoteImpl(get()) } bind CarsRemote::class
  }

  val repositoryModule = module {
    single {  CarsRepositoryImpl(get()) } bind CarsRepository::class
  }


}