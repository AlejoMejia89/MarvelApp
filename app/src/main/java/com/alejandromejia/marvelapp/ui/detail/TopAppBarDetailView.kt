package com.alejandromejia.marvelapp.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.alejandromejia.marvelapp.domain.navigation.Routes
import com.alejandromejia.marvelapp.ui.components.models.ViewModels
import com.alejandromejia.marvelapp.ui.detail.models.ComponentsDetailView
import com.alejandromejia.marvelapp.utils.DETAIL_SCREEN
import com.alejandromejia.marvelapp.utils.EMPTY_STRING
import com.alejandromejia.marvelapp.utils.FontFamilyProvider

@Composable
fun TopAppBarDetailView(
    componentView: ComponentsDetailView,
    navigationController: NavHostController,
    viewModels: ViewModels
) {
    TopAppBar(
        title = {
            Text(
                text = componentView.title,
                color = Color.White,
                fontFamily = FontFamilyProvider.openSans,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth()
            )
        },
        backgroundColor = componentView.background,
        modifier = Modifier.background(Color.Transparent),
        contentColor = Color.Black,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(
                onClick = {
                    if (componentView.view == DETAIL_SCREEN) {
                        navigationController.navigate(Routes.ScaffoldMainView.route)
                    } else {
                        viewModels.mainViewModel.navigateToDetailScreen(
                            ComponentsDetailView(
                                view = DETAIL_SCREEN,
                                title = EMPTY_STRING,
                                background = Color.Black,
                                onBackView = true
                            )
                        )
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    )
}