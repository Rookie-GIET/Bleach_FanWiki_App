package com.blez.bleachfandom.domain.use_cases.search_heroes

import androidx.paging.PagingData
import com.blez.bleachfandom.data.repository.Repository
import com.blez.bleachfandom.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke(query : String) : Flow<PagingData<Hero>>{
        return repository.searchHeroes(query = query)
    }
}