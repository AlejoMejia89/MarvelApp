package com.alejandromejia.marvelapp.ui.comics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.alejandromejia.marvelapp.ui.components.ProgressBarApp
import com.alejandromejia.marvelapp.ui.components.models.ViewModels
import com.alejandromejia.marvelapp.ui.detail.models.ComponentsDetailView

@Composable
fun ComicScreen(
    viewModels: ViewModels,
    componentView: ComponentsDetailView
) {

    val comics by viewModels.comicViewModel.comicView.observeAsState()

    if (comics?.loading == true) {
        ProgressBarApp()
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(componentView.background)
        ) {
            Column {
                comics?.comicsList?.first()
                    ?.let {
                        ComicScreenView(
                            viewModels, it,
                            comics?.comics?.attributionText.toString()
                        )
                    }
            }
        }
    }


}