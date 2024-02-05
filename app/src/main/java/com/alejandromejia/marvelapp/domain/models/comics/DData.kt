package com.alejandromejia.marvelapp.domain.models.comics

data class DData(
    val count: Int,
    val limit: Int,
    val results: List<DResult>,
    val total: Int
)
