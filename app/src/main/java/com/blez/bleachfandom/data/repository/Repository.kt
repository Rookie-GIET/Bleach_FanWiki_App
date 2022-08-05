package com.blez.bleachfandom.data.repository

import androidx.paging.PagingData
import com.blez.bleachfandom.domain.Repository.DataStoreOperation
import com.blez.bleachfandom.domain.Repository.RemoteDataSource
import com.blez.bleachfandom.domain.model.Hero
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject() constructor(
    private val remote : RemoteDataSource,
    private val dataStore : DataStoreOperation
) {
    fun getAllHeroes() : Flow<PagingData<Hero>>{
        return remote.getAllHeroes()
    }
    fun searchHeroes(query : String) : Flow<PagingData<Hero>>{
        return remote.searchHeroes(query = query)
    }

    suspend fun savedOnBoardingState(completed : Boolean){
        dataStore.savedOnBoardingState(completed = completed)
    }
    fun readOnBoardingState(): Flow<Boolean>
    {
        return dataStore.readOnBoardingState()
    }
}