@file:OptIn(ExperimentalMaterial3Api::class)

package com.lihan.noteapp.featrue.note.presentation.detail

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lihan.noteapp.R
import com.lihan.noteapp.core.presentation.NoteAppIcon
import com.lihan.noteapp.featrue.note.presentation.detail.components.ColorSelector
import com.lihan.noteapp.featrue.note.presentation.detail.components.ContentTextField
import com.lihan.noteapp.featrue.note.presentation.detail.model.noteColors
import com.lihan.noteapp.ui.theme.NoteAppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreenRoot(
    viewModel: DetailViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val uiEvent by viewModel.uiEvent.collectAsStateWithLifecycle(DetailUiEvent.Nothing)

    LaunchedEffect(uiEvent){
        when(uiEvent){
            DetailUiEvent.OnGoBack -> onBackClick()
            else -> Unit
        }
    }

    DetailScreen(
        state = state,
        onAction = { action ->
            when(action){
                DetailAction.OnBackClick -> onBackClick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun DetailScreen(
    state: DetailState,
    onAction: (DetailAction) -> Unit
){
    val backgroundColor by animateColorAsState(
        targetValue =  if (state.selectedColor == null){
            noteColors.random()
        }else{
            Color(state.selectedColor)
        }
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .statusBarsPadding()
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            IconButton(
                onClick = {
                    onAction(DetailAction.OnBackClick)
                }
            ) {
                Icon(
                    modifier = Modifier.size(36.dp),
                    imageVector = NoteAppIcon.Back,
                    contentDescription = stringResource(R.string.detail_back_content_description)
                )
            }
            IconButton(
                onClick = {
                    onAction(DetailAction.OnSaveClick)
                }
            ) {
                Icon(
                    modifier = Modifier.size(36.dp),
                    imageVector = NoteAppIcon.Done,
                    contentDescription = stringResource(R.string.detail_save_content_description)
                )
            }
        }
        ColorSelector(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            onColorSelect = {
                onAction(DetailAction.OnSelectColor(it))
            },
            selectedColor = state.selectedColor
        )
        ContentTextField(
            text = state.title,
            textStyle = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.SemiBold
            ),
            placeHolder = stringResource(R.string.detail_tile_text_field_title),
            onValueChange = {
                onAction(DetailAction.OnTitleValueChange(it))
            },
            maxLines = 1,
            placeHolderTextStyle = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = Color.Black.copy(
                    alpha = 0.5f
                )
            )
        )
        Spacer(Modifier.height(4.dp))
        ContentTextField(
            text = state.description,
            textStyle = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.SemiBold
            ),
            placeHolder = stringResource(R.string.detail_tile_text_field_description),
            onValueChange = {
                onAction(DetailAction.OnDescriptionValueChange(it))
            },
            placeHolderTextStyle = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = Color.Black.copy(
                    alpha = 0.5f
                )
            )
        )
    }

}

@PreviewLightDark
@Composable
private fun DetailScreenPreview(){
    NoteAppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.background
                )
                .statusBarsPadding()
        ){
            DetailScreen(
                state = DetailState(
                    noteId = 1,
                    selectedColor = noteColors.random().toArgb()
                ),
                onAction = {}
            )
        }
    }
}