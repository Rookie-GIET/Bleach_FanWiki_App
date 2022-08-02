package com.blez.bleachfandom.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blez.bleachfandom.domain.model.HeroRemoteKeys
import com.blez.bleachfandom.util.Constants.HERO_REMOTE_KEYS_DATABASE_TABLE

@Dao
interface HeroRemoteKeysDAO {
    @Query("SELECT * FROM $HERO_REMOTE_KEYS_DATABASE_TABLE WHERE id =:id")
    suspend fun getRemoteKeys(id:Int) : HeroRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKey(heroRemoteKeys: List<HeroRemoteKeys>)

    @Query("DELETE FROM $HERO_REMOTE_KEYS_DATABASE_TABLE")
    suspend fun deleteAllRemoteKey()

}