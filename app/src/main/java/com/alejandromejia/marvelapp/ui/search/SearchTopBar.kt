package com.alejandromejia.marvelapp.ui.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alejandromejia.marvelapp.domain.models.search.DRecentSearch
import com.alejandromejia.marvelapp.ui.components.models.ViewModels
import com.alejandromejia.marvelapp.utils.CANCEL
import com.alejandromejia.marvelapp.utils.EMPTY_STRING
import com.alejandromejia.marvelapp.utils.SEARCH

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTopBar(viewModels: ViewModels) {
    val focusRequester = remember { FocusRequester() }
    val focused by viewModels.searchViewModel.focused.observeAsState(false)
    val query by viewModels.searchViewModel.query.observeAsState(EMPTY_STRING)
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    var onSearchFocusChange by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Surface(
            modifier = Modifier
                .then(
                    Modifier
                        .height(50.dp)
                        .padding(
                            start = 16.dp,
                            top = 8.dp, bottom = 6.dp,
                            end = if (!focused) 16.dp else 10.dp
                        )
                )
                .weight(1f),
            color = Color.LightGray,
            shape = RoundedCornerShape(percent = 25),
        ) {

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                ) {

                    SearchHint(Modifier.padding(start = 8.dp, end = 8.dp), query)

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        BasicTextField(
                            value = query,
                            onValueChange = {
                                viewModels.searchViewModel.setQuery(it)
                            },
                            modifier = Modifier
                                .wrapContentHeight()
                                .align(Alignment.CenterVertically)
                                .weight(1f)
                                .onFocusChanged {
                                    onSearchFocusChange = it.isFocused
                                }
                                .focusRequester(focusRequester)
                                .padding(start = 34.dp, end = 8.dp),
                            singleLine = true,
                            interactionSource = remember { MutableInteractionSource() }
                                .also { interactionSource ->
                                    LaunchedEffect(interactionSource) {
                                        interactionSource.interactions.collect {
                                            if (it is PressInteraction.Release) {
                                                viewModels.localRecentSearchViewModel.getFavoritesComics()
                                                viewModels.searchViewModel.isFocused(focused = true)
                                            }
                                        }
                                    }
                                },
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Search
                            ),
                            keyboardActions = KeyboardActions(onSearch = {
                                viewModels.localRecentSearchViewModel.saveFavoriteComics(
                                    DRecentSearch(id = null, searchText = query)
                                )
                                viewModels.searchViewModel.getItems(
                                    query
                                )
                                viewModels.searchViewModel.isFocused(focused = false)
                                focusManager.clearFocus()
                                keyboardController?.hide()
                            })
                        )
                    }
                }
            }

        }

        if (focused) {
            Surface(
                modifier = Modifier
                    .weight(0.2f)
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically)
                    .padding(end = 12.dp),
                color = Color.Black
            ) {
                AnimatedVisibility(
                    visible = focused,
                ) {
                    // Back button
                    Text(
                        text = CANCEL,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .clickable {
                                viewModels.searchViewModel.isFocused(focused = false)
                                focusManager.clearFocus()
                                keyboardController?.hide()
                                viewModels.searchViewModel.setQuery(EMPTY_STRING)
                                viewModels.searchViewModel.searchItemsView.value?.charactersList =
                                    emptyList()
                            }
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchHint(modifier: Modifier = Modifier, query: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.then(modifier)

    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = Color(0xff757575)
        )
        if (query.isEmpty()) {
            Text(
                modifier = Modifier.padding(start = 5.dp),
                color = Color(0xff757575),
                text = SEARCH,
            )
        }
    }
}