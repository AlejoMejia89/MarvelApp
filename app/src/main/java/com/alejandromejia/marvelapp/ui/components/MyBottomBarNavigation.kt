package com.alejandromejia.marvelapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.alejandromejia.marvelapp.R
import com.alejandromejia.marvelapp.ui.components.models.ComponentsView
import com.alejandromejia.marvelapp.ui.components.models.ViewModels
import com.alejandromejia.marvelapp.utils.EMPTY_STRING
import com.alejandromejia.marvelapp.utils.FAVORITES
import com.alejandromejia.marvelapp.utils.FAVORITE_SCREEN
import com.alejandromejia.marvelapp.utils.HOME
import com.alejandromejia.marvelapp.utils.HOME_SCREEN
import com.alejandromejia.marvelapp.utils.SEARCH
import com.alejandromejia.marvelapp.utils.SEARCH_SCREEN

@Composable
fun MyBottomBarNavigation(viewModels: ViewModels) {
    var index by rememberSaveable {
        mutableIntStateOf(0)
    }
    BottomNavigation(
        backgroundColor = Color.Black,
        contentColor = Color.White,
        elevation = 10.dp
    ) {
        BottomNavigationItem(
            selected = index == 0,
            onClick = {
                index = 0
                viewModels.mainViewModel.navigateToScreen(
                    ComponentsView(
                        view = HOME_SCREEN,
                        title = EMPTY_STRING,
                        background = Color.Black,
                        onBackView = false
                    )
                )
                viewModels.mainViewModel.lastScreen(
                    ComponentsView(
                        view = HOME_SCREEN,
                        title = EMPTY_STRING,
                        background = Color.Black,
                        onBackView = false
                    )
                )
            },
            icon = {
                Icon(
                    painterResource(id = R.drawable.ic_marvel),
                    contentDescription = EMPTY_STRING,
                    modifier = Modifier
                        .background(Color.White, RoundedCornerShape(24.dp))
                        .size(25.dp)
                )
            },
            label = { Text(text = HOME, color = Color.White) },
            selectedContentColor = Color.Red,
            unselectedContentColor = Color.Gray
        )

        BottomNavigationItem(selected = index == 1,
            onClick = {
                index = 1
                viewModels.mainViewModel.navigateToScreen(
                    ComponentsView(
                        view = SEARCH_SCREEN,
                        title = EMPTY_STRING,
                        background = Color.Black,
                        onBackView = false,
                    )
                )
                viewModels.mainViewModel.lastScreen(
                    ComponentsView(
                        view = SEARCH_SCREEN,
                        title = EMPTY_STRING,
                        background = Color.Black,
                        onBackView = false,
                    )
                )
            }, icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
            }, label = { Text(text = SEARCH, color = Color.White) },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.Gray
        )

        BottomNavigationItem(selected = index == 2, onClick = {
            index = 2
            viewModels.mainViewModel.navigateToScreen(
                ComponentsView(
                    view = FAVORITE_SCREEN,
                    title = FAVORITES,
                    background = Color.Black,
                    onBackView = false
                )
            )
            viewModels.mainViewModel.lastScreen(
                ComponentsView(
                    view = FAVORITE_SCREEN,
                    title = EMPTY_STRING,
                    background = Color.Black,
                    onBackView = false
                )
            )
        }, icon = {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
        }, label = { Text(text = FAVORITES, color = Color.White) },
            selectedContentColor = Color.Red,
            unselectedContentColor = Color.Gray
        )
    }
}