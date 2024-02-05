package com.alejandromejia.marvelapp.domain.models.characters

data class DComics(
    val available: Int,
    val collectionURI: String,
    val items: List<DItem>,
    val returned: Int
)
