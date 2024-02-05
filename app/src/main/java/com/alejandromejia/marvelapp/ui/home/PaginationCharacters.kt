package com.alejandromejia.marvelapp.ui.home


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alejandromejia.marvelapp.R
import com.alejandromejia.marvelapp.ui.components.models.ViewModels
import com.alejandromejia.marvelapp.ui.home.models.CharactersView

@Composable
fun PaginationCharacters(
    viewModels: ViewModels,
    characters: CharactersView?,
    modifier: Modifier
) {
    val page by viewModels.charactersViewModel.page.observeAsState(1)
    val totalPages = (characters?.characters?.data?.total?.div(20) ?: 0) + 1

    Row(modifier = modifier) {
        IconButton(onClick = {
            viewModels.charactersViewModel.nextPage(1)
        }, enabled = page > 1) {
            if (page > 1) {
                Icon(
                    painter = painterResource(id = R.drawable.first_element_arrow),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        IconButton(onClick = {
            viewModels.charactersViewModel.nextPage(page - 1)
        }, enabled = page > 1) {
            if (page > 1) {
                Icon(
                    painter = painterResource(id = R.drawable.prev_element_arrow),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )


            }
        }

        Text(
            text = "$page of $totalPages",
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            color = Color.White
        )


        IconButton(onClick = {
            viewModels.charactersViewModel.nextPage(page + 1)
        }, enabled = page < totalPages) {
            if (page < totalPages) {
                Icon(
                    painter = painterResource(id = R.drawable.next_element_arrow),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        IconButton(onClick = {
            viewModels.charactersViewModel.nextPage(totalPages)
        }, enabled = page < totalPages) {
            if (page < totalPages) {
                Icon(
                    painter = painterResource(id = R.drawable.last_element_arrow),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}