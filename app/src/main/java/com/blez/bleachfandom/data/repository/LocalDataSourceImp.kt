package com.blez.bleachfandom.data.repository

import com.blez.bleachfandom.data.local.dao.BleachDatabase
import com.blez.bleachfandom.domain.Repository.LocalDataSource
import com.blez.bleachfandom.domain.model.Hero

class LocalDataSourceImp(bleachDatabase: BleachDatabase) : LocalDataSource {
    private val heroDAO = bleachDatabase.heroDAO()
    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDAO.getSelectedHero(heroId)
    }
}