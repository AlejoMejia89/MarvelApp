package com.alejandromejia.marvelapp.ui.detail.models

import com.alejandromejia.marvelapp.domain.models.comics.DComics
import com.alejandromejia.marvelapp.domain.models.comics.DResult

data class ComicsView(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val isEmpty: Boolean = false,
    val comicsList: List<DResult>? = null,
    val comics: DComics? = null,
)
