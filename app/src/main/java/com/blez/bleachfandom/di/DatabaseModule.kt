package com.blez.bleachfandom.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.blez.bleachfandom.data.local.dao.BleachDatabase
import com.blez.bleachfandom.data.repository.LocalDataSourceImp
import com.blez.bleachfandom.domain.Repository.LocalDataSource
import com.blez.bleachfandom.util.Constants.BLEACH_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
   fun provideDatabase(
       @ApplicationContext context: Context) : BleachDatabase {
       return Room.databaseBuilder(context,BleachDatabase::class.java,BLEACH_DATABASE).build()
   }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database : BleachDatabase
    ) : LocalDataSource{
        return LocalDataSourceImp(
            bleachDatabase = database
        )
    }


}