package com.lihan.noteapp.featrue.note.presentation.detail

import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.lihan.noteapp.core.domain.Timestamp
import com.lihan.noteapp.core.presentation.Route
import com.lihan.noteapp.featrue.note.domain.model.Note
import com.lihan.noteapp.featrue.note.domain.repository.NoteDetailRepository
import com.lihan.noteapp.featrue.note.presentation.detail.model.noteColors
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: NoteDetailRepository
): ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow().onStart {
        getNote()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        _state.value
    )

    private val _uiEvent = Channel<DetailUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


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
                saveNote()
            }
            else -> Unit
        }
    }

    private fun getNote() {
        val noteId = savedStateHandle.toRoute<Route.NoteDetail>().noteId
        if (noteId != null){
            viewModelScope.launch {
                val getNoteJob = async { repository.getNoteById(noteId?:0).firstOrNull() }.await()
                val title = getNoteJob?.title?:""
                val description = getNoteJob?.description?:""
                val colorArgb = getNoteJob?.color
                _state.update {
                    it.copy(
                        noteId = noteId,
                        title = title,
                        description = description,
                        selectedColor = colorArgb
                    )
                }
            }
        }
    }

    private fun saveNote() {
        viewModelScope.launch {
            val noteId = state.value.noteId
            state.value.also { state ->
                val title = state.title
                val description = state.description
                val colorArgb = state.selectedColor?: noteColors.first().toArgb()
                val timestamp = Timestamp.getNowTimestamp()
                if (noteId == null){
                    repository.createNote(
                        Note(
                            title = title,
                            description = description,
                            color = colorArgb,
                            timestamp = timestamp
                        )
                    )
                }else{
                    repository.updateNote(
                        Note(
                            id = noteId,
                            title = title,
                            description = description,
                            color = colorArgb,
                            timestamp = timestamp
                        )
                    )
                }
            }
            _uiEvent.send(DetailUiEvent.OnGoBack)
        }
    }
}

