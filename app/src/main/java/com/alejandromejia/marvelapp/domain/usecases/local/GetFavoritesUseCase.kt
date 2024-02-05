package com.alejandromejia.marvelapp.domain.usecases.local

import com.alejandromejia.marvelapp.data.local.repository.LocalMarvelRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: LocalMarvelRepository
) {
    operator fun invoke() = repository.getFavoritesCharacters()
}