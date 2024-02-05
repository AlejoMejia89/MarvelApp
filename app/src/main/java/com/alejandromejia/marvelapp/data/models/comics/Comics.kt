package com.alejandromejia.marvelapp.data.models.comics

import com.alejandromejia.marvelapp.domain.models.comics.DComics
import com.alejandromejia.marvelapp.utils.orZero

data class Comics(
    val attributionText: String?,
    val code: Int?,
    val copyright: String?,
    val data: Data,
    val etag: String?,
    val status: String?
) {
    fun toDomainObject() = DComics(
        attributionText = attributionText.orEmpty(),
        code = code.orZero(),
        copyright = copyright.orEmpty(),
        data = data.toDomainObject(),
        etag = etag.orEmpty(),
        status = status.orEmpty()
    )
}
