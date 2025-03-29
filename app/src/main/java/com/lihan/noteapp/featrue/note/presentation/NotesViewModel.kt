package com.lihan.noteapp.featrue.note.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lihan.noteapp.featrue.note.domain.model.Note
import com.lihan.noteapp.featrue.note.domain.repository.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesViewModel(
//    private val repository: NotesRepository
): ViewModel() {

    private val _state = MutableStateFlow(NotesState())
    val state = _state
        .asStateFlow()
        .onStart {
            loadNotes()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _state.value
        )

    private var currentNotes = emptyList<Note>()

    fun onAction(action: NotesAction){
        when(action){
            is NotesAction.OnNoteSearch -> {
                searchNote(action.title)
            }
            else -> Unit
        }
    }

    private fun loadNotes() {
        viewModelScope.launch {
//            repository.getNotes().collectLatest { notes ->
//                _state.update {
//                    it.copy(
//                        items = notes
//                    )
//                }
//                currentNotes = notes
//            }
        }
    }

    private fun searchNote(title: String) {
        if (title.trim().isEmpty()){
            _state.update {
                it.copy(
                    items = currentNotes
                )
            }
            return
        }
        val searchedNotes = currentNotes.filter {
            it.title.contains(title)
        }
        _state.update {
            it.copy(
                items = searchedNotes,
                searchText = title
            )
        }
    }

}