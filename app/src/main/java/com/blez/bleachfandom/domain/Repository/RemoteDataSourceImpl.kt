package com.blez.bleachfandom.domain.Repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.blez.bleachfandom.data.local.dao.BleachDatabase
import com.blez.bleachfandom.data.paging_source.HeroRemoteMediator
import com.blez.bleachfandom.data.paging_source.SearchHeroesSource
import com.blez.bleachfandom.data.remote.BleachApi
import com.blez.bleachfandom.domain.model.Hero
import com.blez.bleachfandom.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val bleachApi: BleachApi,
    private val bleachDatabase: BleachDatabase
) : RemoteDataSource {
    private val heroDAO = bleachDatabase.heroDAO()
    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = {heroDAO.getAllHeroes()}
        return Pager(config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = HeroRemoteMediator(
                bleachApi =  bleachApi, bleachDatabase =  bleachDatabase),
            pagingSourceFactory = pagingSourceFactory
        ).flow

    }

    override fun searchHeroes(query: String): Flow<PagingData<Hero>> {
       return Pager(
           config = PagingConfig(pageSize = ITEMS_PER_PAGE),
           pagingSourceFactory = {
               SearchHeroesSource(bleachApi = bleachApi, query = query)
           }
       ).flow
    }
}