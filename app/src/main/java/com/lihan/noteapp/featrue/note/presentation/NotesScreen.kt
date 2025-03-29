package com.lihan.noteapp.featrue.note.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lihan.noteapp.R
import com.lihan.noteapp.core.presentation.NoteAppIcon
import com.lihan.noteapp.featrue.note.domain.model.Note
import com.lihan.noteapp.featrue.note.presentation.components.NoteItem
import com.lihan.noteapp.featrue.note.presentation.components.SearchBar
import com.lihan.noteapp.featrue.note.presentation.detail.model.noteColors
import com.lihan.noteapp.ui.theme.NoteAppTheme
import java.time.LocalDateTime
import java.time.ZoneId

@Composable
fun NotesScreenRoot(
    viewModel: NotesViewModel,
    navigateToDetail: (Int?) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    NotesScreen(
        state = state,
        onAction = { action ->
            when(action){
                is NotesAction.NavigateToDetail -> navigateToDetail(action.id)
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
private fun NotesScreen(
    state: NotesState,
    onAction: (NotesAction) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onAction(NotesAction.NavigateToDetail(null))
                },
                containerColor = MaterialTheme.colorScheme.background,
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 20.dp)
            ) {
                Icon(
                    imageVector = NoteAppIcon.Add,
                    contentDescription = stringResource(R.string.notes_add)
                )
            }
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = stringResource(R.string.notes_title),
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
            SearchBar(
                text = state.searchText,
                onValueChange = { text ->
                    onAction(NotesAction.OnNoteSearch(text))
                },
                placeHolder = stringResource(R.string.search_bar_placeholder)
            )
            Spacer(Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier.fillMaxWidth().weight(1f)
            ){
                items(state.items){ note ->
                    NoteItem(
                        note = note
                    )
                }
            }
        }

    }

}

@Composable
@PreviewLightDark
private fun NotesScreenPreview() {
    NoteAppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.background
                )
                .statusBarsPadding()
        ){
            NotesScreen(
                state = NotesState(
                    items = (0..10).map {
                        Note(
                            title = "How are you? I'm fine Thank you!",
                            description = "werwe132nlrk2r2lkrr3 \n werwe132nlrk2r2lkrr3\nwerwe132nlrk2r2lkrr3",
                            timestamp = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond(),
                            color = noteColors.random().toArgb()
                        )
                    }
                ),
                onAction = {}
            )
        }
    }
}

