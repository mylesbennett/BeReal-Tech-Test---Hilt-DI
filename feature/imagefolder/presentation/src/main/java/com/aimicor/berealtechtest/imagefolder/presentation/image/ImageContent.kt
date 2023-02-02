package com.aimicor.berealtechtest.imagefolder.presentation.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.aimicor.berealtechtest.imagefolder.presentation.ErrorContent
import com.aimicor.berealtechtest.imagefolder.presentation.FetchStatus
import com.aimicor.berealtechtest.imagefolder.presentation.LoadingContent
import com.aimicor.berealtechtest.imagefolder.presentation.R

private val ByteArray.bitmap: Bitmap
    get() = BitmapFactory.decodeByteArray(this, 0, size)

@Composable
internal fun ImageContent(
    state: ImageUiState,
    event: (ImageEvent) -> Unit
) {
    when (state.fetchStatus) {
        FetchStatus.LOADING -> LoadingContent()
        FetchStatus.SUCCESS -> RenderImage(state.imageData.bitmap, event)
        FetchStatus.FAIL -> ErrorContent()
    }
}

@Composable
private fun RenderImage(
    bitmap: Bitmap,
    event: (ImageEvent) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = rememberImagePainter(bitmap),
            contentDescription = "",
            contentScale = ContentScale.Fit
        )
        Icon(
            modifier = Modifier
                .clickable { event(ImageEvent.OnCloseClicked) }
                .padding(10.dp),
            painter = painterResource(R.drawable.ic_baseline_arrow_back_24),
            contentDescription = ""
        )
    }
}
