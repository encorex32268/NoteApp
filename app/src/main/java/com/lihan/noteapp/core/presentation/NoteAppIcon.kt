package com.lihan.noteapp.core.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.lihan.noteapp.R

object NoteAppIcon {
    val DarkMode: ImageVector
        @Composable get() = ImageVector.vectorResource(R.drawable.dark_mode)
    val LightMode: ImageVector
        @Composable get() = ImageVector.vectorResource(R.drawable.light_mode)
    val Search: ImageVector
        @Composable get() = ImageVector.vectorResource(R.drawable.search)
    val Add = Icons.Default.Add
    val Back = Icons.AutoMirrored.Default.ArrowBack
    val Done = Icons.Default.Check
}