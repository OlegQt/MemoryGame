package com.memorygame.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val md_theme_light_primary = Color(0xFF476810)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFFC7F089)


val md_theme_dark_primary = Color(0xFFACD370)
val md_theme_dark_onPrimary = Color(0xFF213600)
val md_theme_dark_primaryContainer = Color(0xFF324F00)

@Composable
fun GameTheme(
    isDarkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (!isDarkTheme) lightColors(
        primary = md_theme_light_primary,
        onPrimary = md_theme_light_onPrimary,
        primaryVariant = md_theme_light_primaryContainer

    ) else darkColors(
        primary = md_theme_dark_primary,
        onPrimary = md_theme_dark_onPrimary,
        primaryVariant = md_theme_dark_primaryContainer
    )

    MaterialTheme(
        colors = colorScheme, content = content
    )
}