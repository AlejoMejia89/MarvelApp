package com.alejandromejia.marvelapp.data.local.repository

import com.alejandromejia.marvelapp.data.local.dao.MarvelDao
import com.alejandromejia.marvelapp.data.local.mapper.MapperComics
import com.alejandromejia.marvelapp.data.local.models.ComicsFavoritesEntity
import com.alejandromejia.marvelapp.domain.models.comics.DFavoritesComics
import com.alejandromejia.marvelapp.domain.models.comics.DResult
import com.alejandromejia.marvelapp.domain.repository.ILocalComicsRepository

class LocalComicsRepository(
    private val marvelDao: MarvelDao
) : ILocalComicsRepository {

    private val mapperComics = MapperComics

    override fun saveFavoriteComics(model: DResult) {
        val entity: ComicsFavoritesEntity = mapperComics.mapperToFavoriteComicsEntity(model)
        marvelDao.insertFavoriteComic(entity)
    }

    override fun removeFavoriteComics(model: DResult) {
        val entity: ComicsFavoritesEntity = mapperComics.mapperToFavoriteComicsEntity(model)
        marvelDao.removeFavoriteComic(entity)
    }

    override fun getFavoritesComics(): List<DFavoritesComics> {
        return mapperComics.mapperEntityToFavoriteComicsList(marvelDao.getFavoritesComics())
    }
}