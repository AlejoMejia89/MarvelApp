package com.alejandromejia.marvelapp.ui.favorites

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alejandromejia.marvelapp.domain.models.characters.DFavorites

@Composable
fun FavoriteScreenView(
    listState: LazyListState,
    charactersList: List<DFavorites>?
) {
    if (charactersList?.isNotEmpty() == true) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            state = listState,
            contentPadding = PaddingValues(5.dp),
        ) {
            items(charactersList) { item ->
                ItemsFavorite(
                    item = item
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}