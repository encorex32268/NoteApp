package com.lihan.noteapp.featrue.note.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.lihan.noteapp.core.presentation.Route
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class DetailViewModel(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow().onStart {
        val noteId = savedStateHandle.toRoute<Route.NoteDetail>().noteId
        _state.update {
            it.copy(
                noteId = noteId
            )
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        _state.value
    )


    fun onAction(action: DetailAction){
        when(action){
            is DetailAction.OnTitleValueChange -> {
                _state.update {
                    it.copy(
                        title = action.text
                    )
                }
            }
            is DetailAction.OnDescriptionValueChange -> {
                _state.update {
                    it.copy(
                        description = action.text
                    )
                }
            }
            is DetailAction.OnSelectColor -> {
                _state.update {
                    it.copy(
                        selectedColor = action.color
                    )
                }
            }
            DetailAction.OnSaveClick -> {
                //TODO Save or Update Note
            }
            else -> Unit
        }
    }
}