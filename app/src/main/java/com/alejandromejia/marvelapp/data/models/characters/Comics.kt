package com.alejandromejia.marvelapp.data.models.characters

import com.alejandromejia.marvelapp.domain.models.characters.DComics
import com.alejandromejia.marvelapp.utils.orZero

data class Comics(
    val available: Int?,
    val collectionURI: String?,
    val items: List<Item>?,
    val returned: Int?
) {
    fun toDomainObject() = DComics(
        available = available.orZero(),
        collectionURI = collectionURI.orEmpty(),
        items = items?.map { it.toDomainObject() }.orEmpty(),
        returned = returned.orZero()
    )
}