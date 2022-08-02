package com.blez.bleachfandom.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.blez.bleachfandom.data.local.DatabaseConverter
import com.blez.bleachfandom.domain.model.Hero
import com.blez.bleachfandom.domain.model.HeroRemoteKeys

@Database(entities = [Hero::class, HeroRemoteKeys::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverter::class)
abstract class BleachDatabase : RoomDatabase() {
    abstract fun heroDAO():HeroDAO
    abstract fun heroRemoteKeysDAO() : HeroRemoteKeysDAO


}