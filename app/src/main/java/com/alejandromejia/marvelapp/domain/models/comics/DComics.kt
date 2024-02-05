package com.alejandromejia.marvelapp.domain.models.comics

data class DComics(
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val data: DData,
    val etag: String,
    val status: String
)
