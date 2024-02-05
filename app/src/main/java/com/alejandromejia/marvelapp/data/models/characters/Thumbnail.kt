package com.alejandromejia.marvelapp.data.models.characters

import com.alejandromejia.marvelapp.domain.models.characters.DThumbnail

data class Thumbnail(
    val extension: String?,
    val path: String?
) {
    fun toDomainObjects() = DThumbnail(
        extension = extension.orEmpty(),
        path = path.orEmpty()
    )
}