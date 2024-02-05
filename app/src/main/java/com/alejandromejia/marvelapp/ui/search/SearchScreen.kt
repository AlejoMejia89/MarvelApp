package com.alejandromejia.marvelapp.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alejandromejia.marvelapp.ui.components.ProgressBarApp
import com.alejandromejia.marvelapp.ui.components.models.ComponentsView
import com.alejandromejia.marvelapp.ui.components.models.ViewModels
import com.alejandromejia.marvelapp.ui.search.model.SearchItemsView

@Composable
fun SearchScreen(
    componentView: ComponentsView,
    viewModel: ViewModels,
    listState: LazyStaggeredGridState,
    navigationController: NavHostController
) {
    val focused by viewModel.searchViewModel.focused.observeAsState(false)
    val characters by viewModel.searchViewModel.searchItemsView.observeAsState()
    val recentSearch by viewModel.localRecentSearchViewModel.recentSearch.observeAsState()

    when (focused) {
        true -> RecentSearchScreenView(componentView, viewModel, recentSearch?.recentSearch)
        else -> ValidateSearchView(
            componentView,
            listState,
            viewModel,
            characters,
            navigationController
        )
    }
}

@Composable
fun ValidateSearchView(
    componentView: ComponentsView,
    listState: LazyStaggeredGridState,
    viewModel: ViewModels,
    characters: SearchItemsView?,
    navigationController: NavHostController
) {
    if (characters?.loading == true) {
        ProgressBarApp()
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(componentView.background)
                .padding(top = 16.dp, bottom = 60.dp)
        ) {
            Column {
                SearchScreenView(
                    listState,
                    viewModel,
                    characters?.charactersList,
                    navigationController = navigationController
                )
            }
        }
    }
}
