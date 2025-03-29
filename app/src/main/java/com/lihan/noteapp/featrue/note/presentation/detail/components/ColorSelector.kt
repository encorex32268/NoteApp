package com.lihan.noteapp.featrue.note.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.lihan.noteapp.featrue.note.presentation.detail.model.noteColors
import com.lihan.noteapp.ui.theme.NoteAppTheme

@Composable
fun ColorSelector(
    modifier: Modifier = Modifier,
    colors: List<Color> = noteColors,
    onColorSelect: (Int) -> Unit,
    shape: RoundedCornerShape = RoundedCornerShape(16.dp),
    selectedColor: Int?=0
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(4.dp)
    ){
        items(
            items = colors,
            key = { it.toArgb() }
        ){
            val colorBorder = if (selectedColor == it.toArgb()) {
                Modifier.border(
                    width = 2.dp,
                    shape = shape,
                    color = Color.Black
                )
            }else{
                Modifier
            }
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = shape
                    )
                    .background(
                        color = it,
                        shape = shape
                    )
                    .clip(
                        shape = shape
                    )
                    .clickable {
                        onColorSelect(it.toArgb())
                    }
                    .then(colorBorder)

            )
        }
    }
}


@Composable
@PreviewLightDark
private fun ColorSelectorPreview() {
    NoteAppTheme {
        ColorSelector(
            onColorSelect = {

            }
        )
    }

}