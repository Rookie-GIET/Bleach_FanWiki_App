package com.blez.bleachfandom.domain.use_cases.save_onboarding

import com.blez.bleachfandom.data.repository.Repository

class SaveOnBoardingUseCase(private val repository : Repository) {
    suspend operator fun invoke(completed : Boolean){
        repository.savedOnBoardingState(completed = completed)
    }
}