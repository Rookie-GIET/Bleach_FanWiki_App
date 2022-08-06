package com.blez.bleachfandom.presentation.screens.details

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blez.bleachfandom.domain.model.Hero
import com.blez.bleachfandom.domain.use_cases.UseCases
import com.blez.bleachfandom.util.Constants.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle,

) : ViewModel() {
    private val _selectedHero: MutableStateFlow<Hero?> = MutableStateFlow(null)
    val selectedHero: StateFlow<Hero?> = _selectedHero




    init {
        viewModelScope.launch(Dispatchers.IO) {
            val heroId = savedStateHandle.get<Int>(DETAILS_ARGUMENT_KEY)
            _selectedHero.value  = heroId?.let { useCases.getSelectedHeroUseCase(heroId = heroId)}
            _selectedHero.value?.id?.let { Log.d("Hero",it.toString()) }

        }
    }
    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent : SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    private val _colorPalette : MutableState<Map<String, String>> = mutableStateOf(mapOf())
    val colorPalette : State<Map<String, String>> = _colorPalette

    fun generatedColorPalette(){
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.GenerateColorPalette)
        }
    }
    fun setColorPalette(color : Map<String,String>){
        _colorPalette.value = color

    }
}
sealed class UiEvent{
    object GenerateColorPalette : UiEvent()
}