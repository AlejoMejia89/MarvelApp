package com.alejandromejia.marvelapp.domain.usecases.local

import com.alejandromejia.marvelapp.data.local.repository.LocalMarvelRepository
import com.alejandromejia.marvelapp.domain.models.characters.DResult
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(
    private val repository: LocalMarvelRepository
) {
    operator fun invoke(character: DResult) = repository.removeFavoriteCharacter(character)
}