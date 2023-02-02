package com.aimicor.berealtechtest.imagefolder.presentation.folder

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderDetails
import com.aimicor.berealtechtest.imagefolder.presentation.ErrorContent
import com.aimicor.berealtechtest.imagefolder.presentation.FetchStatus
import com.aimicor.berealtechtest.imagefolder.presentation.LoadingContent
import com.aimicor.berealtechtest.imagefolder.presentation.R

@Composable
internal fun FolderContent(
    state: FolderUiState,
    events: (FolderEvents) -> Unit
) {
    when (state.fetchStatus) {
        FetchStatus.LOADING -> LoadingContent()
        FetchStatus.SUCCESS -> FolderItemsContent(state, events)
        FetchStatus.FAIL -> ErrorContent()
    }
}

@Composable
private fun FolderItemsContent(
    state: FolderUiState,
    events: (FolderEvents) -> Unit
) {
    LazyColumn {
        if (state.isSubfolder) item {
            FolderItemContent(
                icon = { UpIcon() },
                text = "..."
            ) { events(FolderEvents.OnCloseClicked) }
        }
        items(state.folderItems) { item -> FolderItemContents(item, events) }
    }
}

@Composable
private fun FolderItemContents(
    details: ImageFolderDetails,
    events: (FolderEvents) -> Unit
) {
    if (details.isDir) FolderItemContent(
        icon = { FolderIcon() },
        text = details.name
    ) { events(FolderEvents.OnItemClicked(details)) }
    else FolderItemContent(
        icon = { ImageIcon() },
        text = details.name
    ) { events(FolderEvents.OnItemClicked(details)) }
}


@Composable
private fun FolderItemContent(
    icon: @Composable () -> Unit,
    text: String,
    clickEvent: () -> Unit
) {
    Column(
        modifier = Modifier.clickable { clickEvent() }
    ) {
        Row(
            modifier = Modifier
                .padding(start = 10.dp)
                .height(50.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon()
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = text
            )
        }
        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
        )
    }
}

@Composable
private fun UpIcon() {
    Icon(
        painter = painterResource(R.drawable.ic_baseline_drive_folder_upload_24),
        tint = Color.Red,
        contentDescription = ""
    )
}

@Composable
private fun FolderIcon() {
    Icon(
        painter = painterResource(R.drawable.ic_baseline_folder_open_24),
        tint = Color.Magenta,
        contentDescription = ""
    )
}

@Composable
private fun ImageIcon() {
    Icon(
        painter = painterResource(R.drawable.ic_baseline_portrait_24),
        tint = Color.Blue,
        contentDescription = ""
    )
}


