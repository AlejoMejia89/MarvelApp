package com.alejandromejia.marvelapp.data.local.mapper

import com.alejandromejia.marvelapp.data.local.models.ComicsFavoritesEntity
import com.alejandromejia.marvelapp.domain.models.comics.DFavoritesComics
import com.alejandromejia.marvelapp.domain.models.comics.DResult
import com.alejandromejia.marvelapp.utils.HTTP
import com.alejandromejia.marvelapp.utils.HTTPS
import com.alejandromejia.marvelapp.utils.orZero

object MapperComics {

    fun mapperToFavoriteComicsEntity(comic: DResult) = ComicsFavoritesEntity(
        id = comic.id,
        description = comic.description,
        title = comic.title,
        thumbnail = "${comic.thumbnail.path.replace(HTTP, HTTPS)}.${comic.thumbnail.extension}",
        format = comic.format,
        pageCount = comic.pageCount
    )

    fun mapperEntityToFavoriteComicsList(entityList: List<ComicsFavoritesEntity>): List<DFavoritesComics> {
        return entityList.map { comicEntity ->
            DFavoritesComics(
                id = comicEntity.id.orZero(),
                description = comicEntity.description.orEmpty(),
                title = comicEntity.title.orEmpty(),
                thumbnail = comicEntity.thumbnail.orEmpty(),
                format = comicEntity.format.orEmpty(),
                pageCount = comicEntity.pageCount.orZero()
            )
        }
    }

}