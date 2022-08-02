package com.blez.bleachfandom.di

import android.content.Context
import com.blez.bleachfandom.data.repository.DataStoreOperationImpl
import com.blez.bleachfandom.data.repository.Repository
import com.blez.bleachfandom.domain.Repository.DataStoreOperation
import com.blez.bleachfandom.domain.use_cases.UseCases
import com.blez.bleachfandom.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.blez.bleachfandom.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.blez.bleachfandom.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideDataStoreOperations(@ApplicationContext context: Context) : DataStoreOperation{
        return DataStoreOperationImpl(context = context)
    }
    @Provides
    @Singleton
    fun provideUseCases(repository: Repository) : UseCases
    {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            getAllHeroesUseCase = GetAllHeroesUseCase(repository)
        )
    }
}