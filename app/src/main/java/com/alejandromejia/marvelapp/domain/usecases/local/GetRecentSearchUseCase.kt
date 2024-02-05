package com.alejandromejia.marvelapp.domain.usecases.local

import com.alejandromejia.marvelapp.data.local.repository.LocalRecentSearchRepository
import javax.inject.Inject

class GetRecentSearchUseCase @Inject constructor(
    private val repository: LocalRecentSearchRepository
) {
    operator fun invoke() = repository.getRecentSearch()
}