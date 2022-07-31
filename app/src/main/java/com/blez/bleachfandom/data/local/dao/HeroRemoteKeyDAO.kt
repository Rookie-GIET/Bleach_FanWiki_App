package com.blez.bleachfandom.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blez.bleachfandom.domain.model.HeroRemoteKey
import com.blez.bleachfandom.util.Constants.HERO_REMOTE_KEY_DATABASE_TABLE

@Dao
interface HeroRemoteKeyDAO {
    @Query("SELECT * FROM $HERO_REMOTE_KEY_DATABASE_TABLE WHERE id =:id")
    suspend fun getRemoteKey(id:Int) : HeroRemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKey(heroRemoteKeys: List<HeroRemoteKey>)

    @Query("DELETE FROM $HERO_REMOTE_KEY_DATABASE_TABLE")
    suspend fun deleteAllRemoteKey()

}