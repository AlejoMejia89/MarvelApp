package com.alejandromejia.marvelapp.data.local.repository

import com.alejandromejia.marvelapp.data.local.dao.MarvelDao
import com.alejandromejia.marvelapp.data.local.mapper.MapperRecentSearch
import com.alejandromejia.marvelapp.data.local.models.RecentSearchEntity
import com.alejandromejia.marvelapp.domain.models.search.DRecentSearch
import com.alejandromejia.marvelapp.domain.repository.ILocalRecentSearchRepository

class LocalRecentSearchRepository(
    private val marvelDao: MarvelDao
) : ILocalRecentSearchRepository {

    private val mapperRecentSearch = MapperRecentSearch

    override fun saveRecentSearch(model: DRecentSearch) {
        val entity: RecentSearchEntity = mapperRecentSearch.mapperToRecentSearchEntity(model)
        marvelDao.insertRecentSearch(entity)
    }

    override fun removeRecentSearch(model: DRecentSearch) {
        val entity: RecentSearchEntity = mapperRecentSearch.mapperToRecentSearchEntity(model)
        marvelDao.removeRecentSearch(entity)
    }

    override fun getRecentSearch(): List<DRecentSearch> {
        return mapperRecentSearch.mapperEntityToRecentSearchList(marvelDao.getRecentSearch())
    }
}