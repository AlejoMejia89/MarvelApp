package com.alejandromejia.marvelapp.ui.home.models

import com.alejandromejia.marvelapp.domain.models.characters.DCharacters
import com.alejandromejia.marvelapp.domain.models.characters.DResult

data class CharactersView(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val isEmpty: Boolean = false,
    val charactersList: List<DResult>? = emptyList(),
    val characters: DCharacters? = null
)