package com.blez.bleachfandom.domain.use_cases.get_all_heroes

import androidx.paging.PagingData
import com.blez.bleachfandom.data.repository.Repository
import com.blez.bleachfandom.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCase(private val repository : Repository) {
    operator fun invoke() : Flow<PagingData<Hero>>{
        return repository.getAllHeroes()
    }

}