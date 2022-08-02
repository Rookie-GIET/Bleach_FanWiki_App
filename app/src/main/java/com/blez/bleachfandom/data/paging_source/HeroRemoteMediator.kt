package com.blez.bleachfandom.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.blez.bleachfandom.data.local.dao.BleachDatabase
import com.blez.bleachfandom.data.remote.BleachApi
import com.blez.bleachfandom.domain.model.Hero
import com.blez.bleachfandom.domain.model.HeroRemoteKeys
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class HeroRemoteMediator @Inject constructor(
    private val bleachApi: BleachApi,
    private val bleachDatabase: BleachDatabase
) : RemoteMediator<Int, Hero>() {

    private val heroDAO = bleachDatabase.heroDAO()
    private val heroRemoteKeysDAO = bleachDatabase.heroRemoteKeysDAO()
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>): MediatorResult {
       return try {
           val page = when(loadType){
               LoadType.REFRESH -> {
                   val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                   remoteKeys?.nextPage?.minus(1)?:1
               }
               LoadType.PREPEND -> {
                   val remoteKeys = getRemoteKeyForFirstItem(state)
                   val prevPage = remoteKeys?.prevPage
                       ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                   prevPage
               }
               LoadType.APPEND -> {
                   val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?:  return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                   nextPage
               }
           }
        val response = bleachApi.getAllHeroes(page)
            if(response.heroes.isNotEmpty()){
                bleachDatabase.withTransaction {
                    if(loadType == LoadType.REFRESH)
                    {
                        heroDAO.deleteAllHeroes()
                        heroRemoteKeysDAO.deleteAllRemoteKey()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.heroes.map{hero ->
                   HeroRemoteKeys(
                       id = hero.id,
                       prevPage = prevPage,
                       nextPage =nextPage
                   )

                    }
                    heroRemoteKeysDAO.addAllRemoteKey(keys)
                    heroDAO.addHeroes(response.heroes)
                }

            }
            MediatorResult.Success(response.nextPage== null)
        }catch(e : Exception) {
            return MediatorResult.Error(e)
        }
    }




    private suspend fun  getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Hero>): HeroRemoteKeys? {
        return  state.anchorPosition?.let { position->
            state.closestItemToPosition(position)?.id?.let  { id->
               heroRemoteKeysDAO.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Hero>): HeroRemoteKeys? {
        return  state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
        ?.let { hero ->
                 heroRemoteKeysDAO.getRemoteKeys(hero.id)
        }

    }
    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Hero>): HeroRemoteKeys? {
        return  state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { hero ->
                heroRemoteKeysDAO.getRemoteKeys(hero.id)
            }

    }

}