package com.blez.bleachfandom.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blez.bleachfandom.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _onBoadingCompleted = MutableStateFlow(false)
    val onBoardingCompleted : StateFlow<Boolean> = _onBoadingCompleted

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _onBoadingCompleted.value =
                useCases.readOnBoardingUseCase().stateIn(viewModelScope).value
        }
    }
}