package com.arafat1419.armovies.core.di

import com.arafat1419.armovies.core.BuildConfig
import com.arafat1419.armovies.core.data.repository.DataRepository
import com.arafat1419.armovies.core.data.repository.PagingRepository
import com.arafat1419.armovies.core.data.remote.RemoteDataSource
import com.arafat1419.armovies.core.data.remote.api.ApiService
import com.arafat1419.armovies.core.domain.repository.IDataRepository
import com.arafat1419.armovies.core.domain.repository.IPagingRepository
import com.arafat1419.armovies.core.domain.usecase.DataInteractor
import com.arafat1419.armovies.core.domain.usecase.DataUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single<IPagingRepository> {
        PagingRepository(get())
    }
    single<IDataRepository> {
        DataRepository(get())
    }
}

val useCaseModule = module {
    factory<DataUseCase> { DataInteractor(get(), get()) }
}