package com.alejandromejia.marvelapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.alejandromejia.marvelapp.R
import com.alejandromejia.marvelapp.ui.components.models.ComponentsView
import com.alejandromejia.marvelapp.ui.components.models.ViewModels
import com.alejandromejia.marvelapp.ui.search.SearchTopBar
import com.alejandromejia.marvelapp.utils.SEARCH_SCREEN

@Composable
fun MyTopAppBar(
    backgroundColor: Color,
    componentView: ComponentsView,
    viewModels: ViewModels
) {
    when (componentView.view) {
        SEARCH_SCREEN -> SearchScreenTitle(viewModels)
        else -> BasicTitle(componentView, backgroundColor)
    }
}

@Composable
fun BasicTitle(
    componentView: ComponentsView,
    backgroundColor: Color
) {
    TopAppBar(
        title = {
            SelectTitle(componentView)
        },
        backgroundColor = backgroundColor,
        modifier = Modifier.background(Color.Transparent),
        contentColor = Color.Black,
        elevation = 0.dp,
    )
}

@Composable
fun SearchScreenTitle(
    viewModels: ViewModels
) {
    val focused by viewModels.searchViewModel.focused.observeAsState(false)

    Column(modifier = Modifier.background(Color.Black).padding(top = 5.dp)) {
        SearchTopBar(viewModels)
        AnimatedVisibility(visible = focused) {
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                color = Color.LightGray,
                thickness = 0.5.dp
            )
        }
    }
}

@Composable
fun SelectTitle(componentView: ComponentsView) {
    if (componentView.view != SEARCH_SCREEN) {
        Box(modifier = Modifier.fillMaxWidth().padding(top = 10.dp)) {
            Icon(
                painterResource(id = R.drawable.logo_marvel),
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier.align(Alignment.Center).zIndex(20f)
            )
            Divider(
                modifier = Modifier
                    .background(Color.Red, RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .height(10.dp).align(Alignment.Center).zIndex(0f),
                color = Color.Red
            )
        }
    }
}