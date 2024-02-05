package com.alejandromejia.marvelapp.domain.usecases.local

import com.alejandromejia.marvelapp.data.local.repository.LocalRecentSearchRepository
import com.alejandromejia.marvelapp.domain.models.search.DRecentSearch
import javax.inject.Inject

class RemoveRecentSearchUseCase @Inject constructor(
    private val repository: LocalRecentSearchRepository
) {
    operator fun invoke(query: DRecentSearch) = repository.removeRecentSearch(query)
}