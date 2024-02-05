package com.alejandromejia.marvelapp.domain.repository

import com.alejandromejia.marvelapp.domain.models.comics.DFavoritesComics
import com.alejandromejia.marvelapp.domain.models.comics.DResult

interface ILocalComicsRepository {

    fun saveFavoriteComics(model: DResult)

    fun removeFavoriteComics(model: DResult)

    fun getFavoritesComics(): List<DFavoritesComics>

}