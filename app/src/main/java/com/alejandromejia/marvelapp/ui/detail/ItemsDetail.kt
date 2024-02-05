package com.alejandromejia.marvelapp.ui.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alejandromejia.marvelapp.domain.models.comics.DResult
import com.alejandromejia.marvelapp.ui.comics.ImageComicApp
import com.alejandromejia.marvelapp.ui.components.models.ViewModels
import com.alejandromejia.marvelapp.ui.detail.models.ComponentsDetailView
import com.alejandromejia.marvelapp.utils.COMIC_SCREEN
import com.alejandromejia.marvelapp.utils.EMPTY_STRING
import com.alejandromejia.marvelapp.utils.HTTP
import com.alejandromejia.marvelapp.utils.HTTPS

@Composable
fun ItemsDetail(item: DResult, viewModel: ViewModels) {

    Box(modifier = Modifier
        .height(240.dp)
        .width(170.dp)
        .clickable {
            viewModel.comicViewModel.getComicById(item.id)
            viewModel.mainViewModel.navigateToDetailScreen(
                ComponentsDetailView(
                    view = COMIC_SCREEN,
                    title = EMPTY_STRING,
                    background = Color.Black,
                    onBackView = true
                )
            )
        }) {
        ImageComicApp(
            imageUrl = "${
                item.thumbnail.path.replace(
                    HTTP,
                    HTTPS
                )
            }.${item.thumbnail.extension}",
            isFromMainView = true
        )
    }

}