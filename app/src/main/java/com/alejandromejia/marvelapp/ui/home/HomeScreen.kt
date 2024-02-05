package com.alejandromejia.marvelapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.alejandromejia.marvelapp.ui.components.HeaderAppView
import com.alejandromejia.marvelapp.ui.components.ProgressBarApp
import com.alejandromejia.marvelapp.ui.components.models.ComponentsView
import com.alejandromejia.marvelapp.ui.components.models.ViewModels
import com.alejandromejia.marvelapp.utils.FontFamilyProvider
import com.alejandromejia.marvelapp.utils.POPULAR_CHARACTERS


@Composable
fun HomeScreen(
    componentView: ComponentsView,
    listState: LazyStaggeredGridState,
    viewModels: ViewModels,
    navigationController: NavHostController
) {
    val characters by viewModels.charactersViewModel.charactersView.observeAsState()

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
                HeaderAppView()
                Text(
                    text = POPULAR_CHARACTERS,
                    color = Color.White,
                    modifier = Modifier.padding(top = 15.dp, bottom = 5.dp, start = 20.dp),
                    fontFamily = FontFamilyProvider.openSans,
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                PopularCharacters(viewModels, navigationController)
                Text(
                    text = "All Characters ( ${characters?.characters?.data?.total} )",
                    color = Color.White,
                    modifier = Modifier.padding(top = 15.dp, bottom = 5.dp, start = 20.dp),
                    fontFamily = FontFamilyProvider.openSans,
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                PaginationCharacters(
                    viewModels,
                    characters,
                    Modifier.align(Alignment.CenterHorizontally)
                )
                HomeScreenView(
                    listState,
                    viewModels,
                    characters?.charactersList,
                    navigationController
                )
            }
        }
    }
}


