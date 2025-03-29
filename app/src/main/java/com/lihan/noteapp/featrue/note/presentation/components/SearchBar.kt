package com.lihan.noteapp.featrue.note.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.lihan.noteapp.R
import com.lihan.noteapp.core.presentation.NoteAppIcon
import com.lihan.noteapp.ui.theme.NoteAppTheme

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    shape: RoundedCornerShape = RoundedCornerShape(16.dp),
    textColor: Color  = MaterialTheme.colorScheme.onBackground,
    borderColor: Color = MaterialTheme.colorScheme.onBackground,
    searchIconColor: Color = MaterialTheme.colorScheme.onBackground
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            decorationBox = { innerField ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = NoteAppIcon.Search,
                        contentDescription = stringResource(R.string.notes_search),
                        tint = searchIconColor
                    )
                    Spacer(Modifier.width(4.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ){
                        if (text.trim().isEmpty()) {
                            Text(
                                text = placeHolder,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = Color.LightGray.copy(
                                        alpha = 0.5f
                                    )
                                )
                            )
                        }
                        innerField()
                    }

                }
            },
            maxLines = 1,
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = textColor
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .clip(shape)
                .border(
                    width = 0.5.dp,
                    color = borderColor,
                    shape = shape
                )
                .padding(12.dp)
        )
        Spacer(Modifier.width(16.dp))
    }
}

@PreviewLightDark
@Composable
private fun SearchBarPreview(){
    NoteAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                )
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            SearchBar(
                text = "",
                placeHolder = "Ex: Game...",
                onValueChange = {}
            )
            SearchBar(
                text = "!234",
                placeHolder = "Ex: Game...",
                onValueChange = {}
            )

        }
    }
}