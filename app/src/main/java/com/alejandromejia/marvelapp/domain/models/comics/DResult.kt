package com.alejandromejia.marvelapp.domain.models.comics

import com.alejandromejia.marvelapp.domain.models.characters.DThumbnail

data class DResult(
    val description: String,
    val id: Int,
    val title: String,
    val format: String,
    val thumbnail: DThumbnail,
    val pageCount: Int,
    val modified: String,
    var isFavorite: Boolean = false
)
