package com.alejandromejia.marvelapp.domain.repository

import com.alejandromejia.marvelapp.domain.models.characters.DFavorites
import com.alejandromejia.marvelapp.domain.models.characters.DResult

interface ILocalMarvelRepository {

    fun saveFavoriteCharacter(model: DResult)

    fun removeFavoriteCharacter(model: DResult)

    fun getFavoritesCharacters(): List<DFavorites>

}