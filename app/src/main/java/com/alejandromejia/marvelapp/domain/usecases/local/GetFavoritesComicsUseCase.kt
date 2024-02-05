package com.alejandromejia.marvelapp.domain.usecases.local

import com.alejandromejia.marvelapp.data.local.repository.LocalComicsRepository
import javax.inject.Inject

class GetFavoritesComicsUseCase @Inject constructor(
    private val repository: LocalComicsRepository
) {
    operator fun invoke() = repository.getFavoritesComics()
}