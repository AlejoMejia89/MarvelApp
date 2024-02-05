package com.alejandromejia.marvelapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.alejandromejia.marvelapp.ui.components.models.ComponentsView
import com.alejandromejia.marvelapp.ui.components.models.ViewModels
import com.alejandromejia.marvelapp.ui.favorites.FavoriteScreen
import com.alejandromejia.marvelapp.ui.home.HomeScreen
import com.alejandromejia.marvelapp.ui.search.SearchScreen
import com.alejandromejia.marvelapp.utils.EMPTY_STRING
import com.alejandromejia.marvelapp.utils.FAVORITE_SCREEN
import com.alejandromejia.marvelapp.utils.HOME_SCREEN
import com.alejandromejia.marvelapp.utils.ONE_STR
import com.alejandromejia.marvelapp.utils.SEARCH_SCREEN
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldMainView(
    viewModels: ViewModels,
    navigationController: NavHostController,
) {
    val scaffoldState = rememberScaffoldState()
    val homeListState = rememberLazyListState()
    val searchListState = rememberLazyStaggeredGridState()

    val defaultComponentView = ComponentsView(
        view = HOME_SCREEN,
        title = EMPTY_STRING,
        background = Color.Black,
        onBackView = false,
    )

    val componentView by viewModels.mainViewModel.componentView.observeAsState(defaultComponentView)
    viewModels.mainViewModel.lastScreen(defaultComponentView)

    Scaffold(
        topBar = {
            if (componentView.view == SEARCH_SCREEN) {
                MyTopAppBar(
                    backgroundColor = componentView.background,
                    componentView = componentView,
                    viewModels = viewModels
                )
            }
        },
        scaffoldState = scaffoldState,
        bottomBar = {
            MyBottomBarNavigation(viewModels)
        },
        content = {
            Content(
                componentView,
                homeListState,
                viewModels,
                searchListState,
                navigationController
            )
        }
    )
}

@Composable
fun Content(
    componentView: ComponentsView,
    homeListState: LazyListState,
    viewModels: ViewModels,
    searchListState: LazyStaggeredGridState,
    navigationController: NavHostController
) {

    when (componentView.view) {
        HOME_SCREEN -> {
            val page by viewModels.charactersViewModel.page.observeAsState(1)
            val searchPage = when (page != 1) {
                true -> (1 + (page - 1) * 20).toString()
                false -> ONE_STR
            }

            viewModels.charactersViewModel.getAllCharacters(searchPage)
            viewModels.charactersViewModel.getCharacterById()
            HomeScreen(
                componentView = componentView,
                viewModels = viewModels,
                listState = searchListState,
                navigationController = navigationController
            )
        }

        SEARCH_SCREEN -> {
            SearchScreen(
                componentView,
                viewModels,
                searchListState,
                navigationController = navigationController
            )
        }

        FAVORITE_SCREEN -> {
            viewModels.localMarvelViewModel.getFavoritesCharacters()
            viewModels.localComicsViewModel.getFavoritesComics()
            FavoriteScreen(componentView, viewModels, homeListState)
        }
    }
}