package com.alejandromejia.marvelapp.ui.favorites.models

import com.alejandromejia.marvelapp.domain.models.comics.DFavoritesComics

data class FavoritesComicsView(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val isEmpty: Boolean = false,
    val charactersList: List<DFavoritesComics>? = emptyList(),
)
