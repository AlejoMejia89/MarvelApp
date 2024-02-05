package com.alejandromejia.marvelapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alejandromejia.marvelapp.domain.models.characters.DResult
import com.alejandromejia.marvelapp.ui.components.models.ViewModels

@Composable
fun HomeScreenView(
    listState: LazyStaggeredGridState,
    viewModels: ViewModels,
    charactersList: List<DResult>?,
    navigationController: NavHostController
) {

    if (charactersList?.isNotEmpty() == true) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalItemSpacing = 2.dp,
            state = listState
        ) {
            items(charactersList) { item ->
                ItemsHome(
                    item = item,
                    viewModel = viewModels,
                    navigationController = navigationController
                )
            }
        }
    }
}