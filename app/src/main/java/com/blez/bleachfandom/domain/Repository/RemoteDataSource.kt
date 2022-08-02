package com.blez.bleachfandom.domain.Repository

import androidx.paging.PagingData
import com.blez.bleachfandom.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllHeroes() : Flow<PagingData<Hero>>
    fun searchHeroes() : Flow<PagingData<Hero>>
}