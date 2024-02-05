package com.alejandromejia.marvelapp.data.local.repository

import com.alejandromejia.marvelapp.data.local.dao.MarvelDao
import com.alejandromejia.marvelapp.data.local.mapper.MapperMarvel
import com.alejandromejia.marvelapp.data.local.models.FavoritesEntity
import com.alejandromejia.marvelapp.domain.models.characters.DFavorites
import com.alejandromejia.marvelapp.domain.models.characters.DResult
import com.alejandromejia.marvelapp.domain.models.comics.DFavoritesComics
import com.alejandromejia.marvelapp.domain.repository.ILocalMarvelRepository

class LocalMarvelRepository(
    private val marvelDao: MarvelDao
) : ILocalMarvelRepository {

    private val mapperMarvel = MapperMarvel

    override fun saveFavoriteCharacter(model: DResult) {
        val entity: FavoritesEntity = mapperMarvel.mapperToFavoriteEntity(model)
        marvelDao.insertFavorite(entity)
    }

    override fun removeFavoriteCharacter(model: DResult) {
        val entity: FavoritesEntity = mapperMarvel.mapperToFavoriteEntity(model)
        marvelDao.removeFavorite(entity)
    }

    override fun getFavoritesCharacters(): List<DFavorites> {
        return mapperMarvel.mapperEntityToFavoriteMarvelList(marvelDao.getFavoritesCharacters())
    }
}