package com.alejandromejia.marvelapp.data.models.characters

import com.alejandromejia.marvelapp.domain.models.characters.DResult
import com.alejandromejia.marvelapp.utils.orZero

data class Result(
    val comics: Comics,
    val description: String?,
    val id: Int?,
    val name: String?,
    val resourceURI: String?,
    val thumbnail: Thumbnail,
    val urls: List<Url>?
) {
    fun toDomainObject() = DResult(
        comics = comics.toDomainObject(),
        description = description.orEmpty(),
        id = id.orZero(),
        name = name.orEmpty(),
        resourceURI = resourceURI.orEmpty(),
        thumbnail = thumbnail.toDomainObjects(),
        urls = urls?.map { it.toDomainObject() }.orEmpty()
    )
}