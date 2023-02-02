package com.aimicor.berealtechtest.imagefolder.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
internal fun ErrorContent() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.Center),
            painter = painterResource(R.drawable.ic_baseline_error_outline_24),
            tint = Color.Red,
            contentDescription = ""
        )
    }
}
