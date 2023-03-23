package com.onelist.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


private val DarkColorPalette = darkColors(
    primary = JetBlack,
    primaryVariant = DarkGreen,
    secondary = MainGreen,
    background = EerieBlack,
    surface = EerieBlack,
    onPrimary = Snow,
    onSurface = Snow,
    onSecondary = Snow,
    onBackground = Snow,
)

private val LightColorPalette = lightColors(
    primary = MainGreen,
    primaryVariant = LightGreen,
    secondary = Rose,
    background = Snow,
    surface = Snow,
    onPrimary = Snow,
    onSurface = DarkGreen,
    onSecondary = DarkGreen,
    onBackground = DarkGreen,


    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun OneListTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}