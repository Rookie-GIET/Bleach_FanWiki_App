package com.blez.bleachfandom.domain.Repository

import com.blez.bleachfandom.domain.model.Hero

interface LocalDataSource {
    suspend fun getSelectedHero(heroId : Int) : Hero
}