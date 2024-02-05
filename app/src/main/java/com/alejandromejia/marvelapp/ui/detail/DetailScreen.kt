package com.alejandromejia.marvelapp.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.alejandromejia.marvelapp.ui.components.ProgressBarApp
import com.alejandromejia.marvelapp.ui.components.models.ViewModels
import com.alejandromejia.marvelapp.ui.detail.models.ComponentsDetailView

@Composable
fun DetailScreen(
    componentView: ComponentsDetailView,
    viewModels: ViewModels,
    listState: LazyListState
) {
    val characters by viewModels.characterDetailViewModel.characterView.observeAsState()
    val comics by viewModels.characterDetailViewModel.comicsView.observeAsState()

    if (characters?.loading == true) {
        ProgressBarApp()
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(componentView.background)
        ) {
            Column {
                characters?.charactersList?.first()?.let {
                    DetailScreenView(
                        listState,
                        viewModels,
                        it,
                        comics
                    )
                }
            }
        }
    }
}