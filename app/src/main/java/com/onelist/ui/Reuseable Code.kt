package com.onelist.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun categoryDivider() {
    Divider(
        color = MaterialTheme.colors.onBackground,
        modifier = Modifier
            .height(1.dp)
            .fillMaxHeight()
            .fillMaxWidth()
    )
}