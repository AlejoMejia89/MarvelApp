package com.alejandromejia.marvelapp.data.local.mapper

import com.alejandromejia.marvelapp.data.local.models.RecentSearchEntity
import com.alejandromejia.marvelapp.domain.models.search.DRecentSearch
import com.alejandromejia.marvelapp.utils.orZero

object MapperRecentSearch {

    fun mapperToRecentSearchEntity(query: DRecentSearch) = RecentSearchEntity(
        id = query.id,
        searchText = query.searchText
    )

    fun mapperEntityToRecentSearchList(entityList: List<RecentSearchEntity>): List<DRecentSearch> {
        return entityList.map { searchEntity ->
            DRecentSearch(
                id = searchEntity.id.orZero(),
                searchText = searchEntity.searchText.orEmpty()
            )
        }
    }
}