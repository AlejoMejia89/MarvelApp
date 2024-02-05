package com.alejandromejia.marvelapp.ui.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alejandromejia.marvelapp.domain.models.characters.DResult
import com.alejandromejia.marvelapp.ui.components.ImageApp
import com.alejandromejia.marvelapp.ui.components.models.ViewModels
import com.alejandromejia.marvelapp.ui.detail.models.ComicsView
import com.alejandromejia.marvelapp.utils.DESCRIPTION
import com.alejandromejia.marvelapp.utils.FontFamilyProvider
import com.alejandromejia.marvelapp.utils.HTTP
import com.alejandromejia.marvelapp.utils.HTTPS
import com.alejandromejia.marvelapp.utils.NO_DATA_FOUND

@Composable
fun DetailScreenView(
    listState: LazyListState,
    viewModels: ViewModels,
    character: DResult,
    comics: ComicsView?
) {

    Column(Modifier.fillMaxWidth()) {
        Text(
            text = character.name,
            color = Color.White,
            modifier = Modifier
                .padding(bottom = 10.dp, start = 5.dp, end = 5.dp)
                .fillMaxWidth(),
            fontFamily = FontFamilyProvider.openSans,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Box(
            modifier = Modifier
                .size(170.dp)
                .border(BorderStroke(2.dp, Color.Red))
                .align(Alignment.CenterHorizontally)
                .padding(5.dp),
            contentAlignment = Alignment.Center
        ) {
            ImageApp(
                imageUrl = "${
                    character.thumbnail.path.replace(
                        HTTP,
                        HTTPS
                    )
                }.${character.thumbnail.extension}",
                isFromMainView = false
            )
        }
        Text(
            text = DESCRIPTION,
            color = Color.White,
            modifier = Modifier.padding(top = 20.dp, bottom = 10.dp, start = 20.dp),
            fontFamily = FontFamilyProvider.openSans,
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = character.description.ifEmpty { NO_DATA_FOUND },
            color = Color.White,
            fontFamily = FontFamilyProvider.openSans,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            textAlign = if (character.description.isNotEmpty()) TextAlign.Justify else TextAlign.Center,
            fontSize = 12.sp,
            lineHeight = 18.sp,
        )
        Text(
            text = "COMICS ( ${comics?.comics?.data?.total} )",
            color = Color.White,
            modifier = Modifier.padding(top = 20.dp, bottom = 10.dp, start = 20.dp),
            fontFamily = FontFamilyProvider.openSans,
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        if (comics?.comicsList?.isNotEmpty() == true) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                state = listState,
                contentPadding = PaddingValues(5.dp),
            ) {
                items(comics.comicsList) { item ->
                    ItemsDetail(
                        item = item,
                        viewModel = viewModels
                    )
                }
            }
        }

        Text(
            text = "${comics?.comics?.attributionText}",
            color = Color.Gray,
            modifier = Modifier
                .padding(vertical = 15.dp)
                .fillMaxWidth(),
            fontFamily = FontFamilyProvider.openSans,
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            letterSpacing = 0.1.sp
        )
    }
}