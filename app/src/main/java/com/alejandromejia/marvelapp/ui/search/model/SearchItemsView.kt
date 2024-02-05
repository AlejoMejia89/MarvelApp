package com.alejandromejia.marvelapp.ui.search.model

import com.alejandromejia.marvelapp.domain.models.characters.DResult

data class SearchItemsView(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val isEmpty: Boolean = false,
    var charactersList: List<DResult>? = emptyList()
)