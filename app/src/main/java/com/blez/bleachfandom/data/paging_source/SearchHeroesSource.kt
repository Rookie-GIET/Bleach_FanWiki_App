package com.blez.bleachfandom.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.blez.bleachfandom.data.remote.BleachApi
import com.blez.bleachfandom.domain.model.Hero
import javax.inject.Inject

class SearchHeroesSource @Inject constructor(
private val bleachApi: BleachApi,
private val query : String
) : PagingSource<Int,Hero>() {
    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? {
       return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> {
      return try {
          val apiResponse = bleachApi.searchHeroes(name = query)
          val heroes = apiResponse.heroes
          if (heroes.isNotEmpty()){
            LoadResult.Page(
                data = heroes,
                prevKey = apiResponse.prevPage,
                nextKey = apiResponse.nextPage
            )
          }else{
              LoadResult.Page(
                  data = emptyList(),
                  prevKey = null,
                  nextKey = null
              )
          }
      }catch (e : Exception)
      {
            LoadResult.Error(e)
      }
    }
}