@file:OptIn(ExperimentalFoundationApi::class)

package com.lihan.noteapp.featrue.note.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.lihan.noteapp.core.domain.timestampToTimeString
import com.lihan.noteapp.featrue.note.domain.model.Note
import com.lihan.noteapp.featrue.note.presentation.detail.model.noteColors
import com.lihan.noteapp.ui.theme.NoteAppTheme
import java.time.LocalDateTime
import java.time.ZoneId

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    note: Note,
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
    onLongClick: () -> Unit,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                color = Color(note.color),
                shape = shape
            )
            .combinedClickable(
                onLongClick = onLongClick,
                onClick = onClick
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ){
        Text(
            text = note.title,
            style = MaterialTheme.typography.titleLarge.copy(
                color = Color.Black
            ),
            fontWeight = FontWeight.ExtraBold
        )

        Text(
            text = note.description,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.Black
            ),
            fontWeight = FontWeight.Normal
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = note.timestamp.timestampToTimeString(),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.DarkGray,
                textAlign = TextAlign.End
            ),
            fontWeight = FontWeight.Normal
        )
    }

}

@Composable
@PreviewLightDark
private fun NoteItemPreview() {
    NoteAppTheme {
        Column(
            modifier = Modifier.fillMaxSize().background(
                MaterialTheme.colorScheme.onBackground
            ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            NoteItem(
                note = Note(
                    title = "How are you? I'm fine Thank you!",
                    description = "werwe132nlrk2r2lkrr3 \n werwe132nlrk2r2lkrr3\nwerwe132nlrk2r2lkrr3",
                    timestamp = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond(),
                    color = noteColors.random().toArgb()
                ),
                onLongClick = {},
                onClick = {}
            )
        }
    }
}