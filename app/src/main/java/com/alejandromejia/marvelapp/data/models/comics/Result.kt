package com.alejandromejia.marvelapp.data.models.comics

import com.alejandromejia.marvelapp.data.models.characters.Thumbnail
import com.alejandromejia.marvelapp.domain.models.comics.DResult
import com.alejandromejia.marvelapp.utils.orZero

data class Result(
    val description: String?,
    val id: Int?,
    val title: String?,
    val format: String?,
    val thumbnail: Thumbnail,
    val modified: String?,
    val pageCount: Int
) {
    fun toDomainObject() = DResult(
        description = description.orEmpty(),
        id = id.orZero(),
        title = title.orEmpty(),
        format = format.orEmpty(),
        thumbnail = thumbnail.toDomainObjects(),
        modified = modified.orEmpty(),
        pageCount = pageCount.orZero()
    )
}
