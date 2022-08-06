package com.blez.bleachfandom.domain.use_cases.get_selected_hero

import com.blez.bleachfandom.data.repository.Repository
import com.blez.bleachfandom.domain.model.Hero

class GetSelectedHeroUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(heroId : Int) : Hero
    {
        return repository.getSelectedHero(heroId)
    }
}