package com.alejandromejia.marvelapp.ui.favorites

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alejandromejia.marvelapp.ui.components.HeaderAppView
import com.alejandromejia.marvelapp.ui.components.models.ComponentsView
import com.alejandromejia.marvelapp.ui.components.models.ViewModels
import com.alejandromejia.marvelapp.utils.CHARACTERS
import com.alejandromejia.marvelapp.utils.COMICS

@Composable
fun FavoriteScreen(
    componentView: ComponentsView,
    viewModels: ViewModels,
    listState: LazyListState
) {
    val characters by viewModels.localMarvelViewModel.favoriteCharacters.observeAsState()
    val comics by viewModels.localComicsViewModel.favoriteComics.observeAsState()
    var isCharacterVisible by rememberSaveable { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(componentView.background)
            .padding(top = 16.dp, bottom = 60.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            HeaderAppView()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp, top = 20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(
                    onClick = {
                        isCharacterVisible = !isCharacterVisible
                    },
                    border = BorderStroke(
                        1.dp,
                        if (isCharacterVisible) Color.White else Color.Black
                    ),
                    modifier = Modifier.padding(end = 5.dp)
                ) {
                    Text(
                        text = CHARACTERS,
                        color = Color.White,
                    )
                }
                TextButton(
                    onClick = {
                        isCharacterVisible = !isCharacterVisible
                    },
                    border = BorderStroke(
                        1.dp,
                        if (!isCharacterVisible) Color.White else Color.Black
                    ),
                    modifier = Modifier.padding(start = 5.dp)
                ) {
                    Text(
                        text = COMICS,
                        color = Color.White,
                    )
                }
            }
            Column {
                if (isCharacterVisible) {
                    FavoriteScreenView(listState, characters?.charactersList)
                } else {
                    FavoriteComicsScreenView(
                        listState = listState,
                        comicsList = comics?.charactersList
                    )
                }
            }
        }
    }
}