package com.alejandromejia.marvelapp.data.models.characters

import com.alejandromejia.marvelapp.domain.models.characters.DCharacters
import com.alejandromejia.marvelapp.utils.orZero

data class Characters(
    val attributionText: String?,
    val code: Int?,
    val copyright: String?,
    val data: Data,
    val etag: String?,
    val status: String?
) {
    fun toDomainObject() = DCharacters(
        attributionText = attributionText.orEmpty(),
        code = code.orZero(),
        copyright = copyright.orEmpty(),
        data = data.toDomainObject(),
        etag = etag.orEmpty(),
        status = status.orEmpty()
    )
}

