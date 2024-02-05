package com.alejandromejia.marvelapp.ui.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.alejandromejia.marvelapp.ui.comics.ComicScreen
import com.alejandromejia.marvelapp.ui.components.models.ViewModels
import com.alejandromejia.marvelapp.ui.detail.models.ComponentsDetailView
import com.alejandromejia.marvelapp.utils.COMIC_SCREEN
import com.alejandromejia.marvelapp.utils.DETAIL_SCREEN
import com.alejandromejia.marvelapp.utils.EMPTY_STRING

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldDetailView(
    viewModels: ViewModels,
    navigationController: NavHostController,
) {
    val scaffoldState = rememberScaffoldState()
    val listState = rememberLazyListState()

    val componentsDetailView by viewModels.mainViewModel.componentDetailView.observeAsState(
        ComponentsDetailView(
            view = DETAIL_SCREEN,
            title = EMPTY_STRING,
            background = Color.Black,
            onBackView = true
        )
    )

    Scaffold(
        topBar = {
            TopAppBarDetailView(
                componentView = componentsDetailView,
                navigationController = navigationController,
                viewModels = viewModels
            )
        },
        scaffoldState = scaffoldState,
        content = {
            ContentDetail(
                componentsDetailView,
                viewModels,
                listState
            )
        }
    )
}

@Composable
fun ContentDetail(
    componentView: ComponentsDetailView,
    viewModels: ViewModels,
    listState: LazyListState
) {
    when (componentView.view) {
        DETAIL_SCREEN -> DetailScreen(componentView, viewModels, listState)
        COMIC_SCREEN -> ComicScreen(viewModels, componentView)
    }
}
