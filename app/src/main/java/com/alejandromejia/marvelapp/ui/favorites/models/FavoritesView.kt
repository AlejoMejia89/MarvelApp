package com.alejandromejia.marvelapp.ui.favorites.models

import com.alejandromejia.marvelapp.domain.models.characters.DFavorites

data class FavoritesView(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val isEmpty: Boolean = false,
    val charactersList: List<DFavorites>? = emptyList(),
)