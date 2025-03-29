package com.lihan.noteapp.featrue.note.presentation.detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.lihan.noteapp.ui.theme.NoteAppTheme

@Composable
fun ContentTextField(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    placeHolderTextStyle: TextStyle,
    maxLines: Int = Int.MAX_VALUE
) {
    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        decorationBox = { innerField ->
            if (text.trim().isEmpty()) {
                Text(
                    text = placeHolder,
                    style = placeHolderTextStyle
                )
            }
            innerField()
        },
        maxLines = maxLines,
        textStyle = textStyle,
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .padding(12.dp)
    )
}

@Composable
@PreviewLightDark
private fun ContentTextFieldPreview() {
    NoteAppTheme {

    }
}