package com.alejandromejia.marvelapp.domain.models.comics

data class DFavoritesComics(
    val description: String,
    val id: Int,
    val title: String,
    val format: String,
    val thumbnail: String,
    val pageCount: Int
)
