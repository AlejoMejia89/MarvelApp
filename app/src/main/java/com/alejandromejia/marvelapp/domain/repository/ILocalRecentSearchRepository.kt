package com.alejandromejia.marvelapp.domain.repository

import com.alejandromejia.marvelapp.domain.models.search.DRecentSearch

interface ILocalRecentSearchRepository {

    fun saveRecentSearch(model: DRecentSearch)

    fun removeRecentSearch(model: DRecentSearch)

    fun getRecentSearch(): List<DRecentSearch>

}