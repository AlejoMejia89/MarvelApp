package com.alejandromejia.marvelapp.data.local.mapper

import com.alejandromejia.marvelapp.data.local.models.FavoritesEntity
import com.alejandromejia.marvelapp.domain.models.characters.DFavorites
import com.alejandromejia.marvelapp.domain.models.characters.DResult
import com.alejandromejia.marvelapp.utils.HTTP
import com.alejandromejia.marvelapp.utils.HTTPS
import com.alejandromejia.marvelapp.utils.orZero

object MapperMarvel {

    fun mapperToFavoriteEntity(model: DResult) = FavoritesEntity(
        id = model.id,
        description = model.description,
        name = model.name,
        thumbnail = "${model.thumbnail.path.replace(HTTP, HTTPS)}.${model.thumbnail.extension}"
    )

    fun mapperEntityToFavoriteMarvelList(entityList: List<FavoritesEntity>): List<DFavorites> {
        return entityList.map { marvelEntity ->
            DFavorites(
                id = marvelEntity.id.orZero(),
                description = marvelEntity.description.orEmpty(),
                name = marvelEntity.name.orEmpty(),
                thumbnail = marvelEntity.thumbnail.orEmpty(),
            )
        }
    }
}