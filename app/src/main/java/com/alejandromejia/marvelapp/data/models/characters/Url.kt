package com.alejandromejia.marvelapp.data.models.characters

import com.alejandromejia.marvelapp.domain.models.characters.DUrl

data class Url(
    val type: String?,
    val url: String?
) {
    fun toDomainObject() = DUrl(
        type = type.orEmpty(),
        url = url.orEmpty()
    )
}