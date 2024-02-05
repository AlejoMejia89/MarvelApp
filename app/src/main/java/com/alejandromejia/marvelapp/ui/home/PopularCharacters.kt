package com.alejandromejia.marvelapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alejandromejia.marvelapp.domain.navigation.Routes
import com.alejandromejia.marvelapp.ui.components.ImageApp
import com.alejandromejia.marvelapp.ui.components.models.ViewModels
import com.alejandromejia.marvelapp.utils.HTTP
import com.alejandromejia.marvelapp.utils.HTTPS

@Composable
fun PopularCharacters(
    viewModels: ViewModels,
    navigationController: NavHostController
) {
    val characters by viewModels.charactersViewModel.charactersView1.observeAsState()

    characters?.let { characterList ->
        if (characterList.isNotEmpty()) {
            LazyRow(
                modifier = Modifier.padding(top = 10.dp, start = 25.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                items(characterList.size) { character ->
                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .height(80.dp)
                            .padding(top = 5.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
                            .clip(shape = RoundedCornerShape(100))
                            .clickable {
                                navigationController.navigate(Routes.ScaffoldDetailView.route)
                                viewModels.characterDetailViewModel.getCharacterById(characterList[character].id)
                            }
                    ) {
                        ImageApp(
                            imageUrl = "${
                                characterList[character].thumbnail.path.replace(
                                    HTTP,
                                    HTTPS
                                )
                            }.${characterList[character].thumbnail.extension}",
                            isFromMainView = false
                        )
                    }
                }
            }
        }
    }
}