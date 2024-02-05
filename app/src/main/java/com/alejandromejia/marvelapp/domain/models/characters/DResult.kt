package com.alejandromejia.marvelapp.domain.models.characters

data class DResult(
    val comics: DComics,
    val description: String,
    val id: Int,
    val name: String,
    val resourceURI: String,
    val thumbnail: DThumbnail,
    val urls: List<DUrl>,
    var isFavorite: Boolean = false
)