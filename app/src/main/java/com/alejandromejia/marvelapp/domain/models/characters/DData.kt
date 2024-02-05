package com.alejandromejia.marvelapp.domain.models.characters

data class DData(
    val count: Int,
    val limit: Int,
    val results: List<DResult>,
    val total: Int
)
