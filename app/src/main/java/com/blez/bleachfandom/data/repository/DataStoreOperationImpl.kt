package com.blez.bleachfandom.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.blez.bleachfandom.domain.Repository.DataStoreOperation
import com.blez.bleachfandom.util.Constants.PREFERENCES_KEY
import com.blez.bleachfandom.util.Constants.PREFERENCES_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)
class DataStoreOperationImpl(context: Context) : DataStoreOperation {
    private object PreferencesKey{
        val onBoardingKey = booleanPreferencesKey(name = PREFERENCES_KEY)
    }
    private val dataStore = context.dataStore

    override suspend fun savedOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch {exception  ->
                if(exception  is IOException){
                    emit(emptyPreferences())
                }else{
                    throw exception
                }
            }
            .map {preferences ->
                val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
                onBoardingState
            }
    }
}