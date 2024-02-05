package com.alejandromejia.marvelapp.domain.models.characters

data class DCharacters(
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val data: DData,
    val etag: String,
    val status: String
)
