package com.alejandromejia.marvelapp.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.alejandromejia.marvelapp.R
import com.alejandromejia.marvelapp.domain.models.characters.DResult
import com.alejandromejia.marvelapp.domain.navigation.Routes
import com.alejandromejia.marvelapp.ui.components.ImageApp
import com.alejandromejia.marvelapp.ui.components.models.ViewModels
import com.alejandromejia.marvelapp.utils.HTTP
import com.alejandromejia.marvelapp.utils.HTTPS

@Composable
fun ItemsHome(
    item: DResult,
    viewModel: ViewModels,
    navigationController: NavHostController
) {
    var favoriteClicked by rememberSaveable { mutableStateOf(item.isFavorite) }

    Card(
        modifier = Modifier
            .clickable {
                navigationController.navigate(Routes.ScaffoldDetailView.route)
                viewModel.characterDetailViewModel.getCharacterById(item.id)
            },
        border = BorderStroke(1.dp, Color.Gray),
        backgroundColor = Color.Black
    ) {
        Column {
            ImageApp(
                imageUrl = "${
                    item.thumbnail.path.replace(
                        HTTP,
                        HTTPS
                    )
                }.${item.thumbnail.extension}",
                isFromMainView = true
            )
            Divider(
                modifier = Modifier
                    .background(Color.Red, RectangleShape)
                    .fillMaxWidth()
                    .height(2.dp)
            )
            Text(
                text = item.name,
                fontSize = 11.sp,
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Text(
                text = "${item.comics.available} Comics",
                fontSize = 9.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = Color.LightGray
            )
            Icon(
                painter = painterResource(id = if (favoriteClicked) R.drawable.ic_favorite_check else R.drawable.ic_favorite_uncheck),
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        favoriteClicked = !favoriteClicked
                        if (favoriteClicked) {
                            viewModel.localMarvelViewModel.saveFavorite(item)
                        } else {
                            viewModel.localMarvelViewModel.removeFavorite(item)
                        }
                    }
                    .padding(start = 4.dp, end = 4.dp)
                    .align(Alignment.End)
                    .padding(10.dp),
                tint = if (favoriteClicked) Color.Red else Color.LightGray
            )

        }

    }
}