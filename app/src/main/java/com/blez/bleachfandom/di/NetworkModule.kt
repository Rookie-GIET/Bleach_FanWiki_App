package com.blez.bleachfandom.di

import androidx.paging.ExperimentalPagingApi
import androidx.room.PrimaryKey
import com.blez.bleachfandom.data.local.dao.BleachDatabase
import com.blez.bleachfandom.data.remote.BleachApi
import com.blez.bleachfandom.domain.Repository.RemoteDataSource
import com.blez.bleachfandom.domain.Repository.RemoteDataSourceImpl
import com.blez.bleachfandom.util.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@OptIn(ExperimentalSerializationApi::class)
@Module
@ExperimentalPagingApi
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient() : OkHttpClient{
        return OkHttpClient.Builder()
            .readTimeout(15,TimeUnit.MINUTES)
            .connectTimeout(15,TimeUnit.MINUTES)
            .build()

    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient) : Retrofit{
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideBleachApi(retrofit: Retrofit) : BleachApi{
        return retrofit.create(BleachApi::class.java)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideRemoteDataSource(
        bleachApi: BleachApi,bleachDatabase: BleachDatabase
    ) : RemoteDataSource{
        return RemoteDataSourceImpl(bleachApi =  bleachApi,
        bleachDatabase = bleachDatabase)
    }
}