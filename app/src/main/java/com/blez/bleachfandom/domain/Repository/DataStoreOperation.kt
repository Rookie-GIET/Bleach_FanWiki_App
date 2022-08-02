package com.blez.bleachfandom.domain.Repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperation {
    suspend fun savedOnBoardingState(completed:Boolean)
    fun readOnBoardingState(): Flow<Boolean>
}