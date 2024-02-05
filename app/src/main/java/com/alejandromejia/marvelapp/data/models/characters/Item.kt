package com.alejandromejia.marvelapp.data.models.characters

import com.alejandromejia.marvelapp.domain.models.characters.DItem

data class Item(
    val name: String?,
    val resourceURI: String?
) {
    fun toDomainObject() = DItem(
        name = name.orEmpty(),
        resourceURI = resourceURI.orEmpty()
    )
}