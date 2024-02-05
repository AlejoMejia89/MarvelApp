package com.alejandromejia.marvelapp.data.models.characters

import com.alejandromejia.marvelapp.domain.models.characters.DData
import com.alejandromejia.marvelapp.utils.orZero

data class Data(
    val count: Int?,
    val limit: Int?,
    val results: List<Result>?,
    val total: Int?
) {
    fun toDomainObject() = DData(
        count = count.orZero(),
        limit = limit.orZero(),
        results = results?.map { it.toDomainObject() }.orEmpty(),
        total = total.orZero()
    )
}