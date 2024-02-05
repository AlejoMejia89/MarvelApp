package com.alejandromejia.marvelapp.domain.usecases.local

import com.alejandromejia.marvelapp.data.local.repository.LocalComicsRepository
import com.alejandromejia.marvelapp.domain.models.comics.DResult
import javax.inject.Inject

class RemoveFavoriteComicUseCase @Inject constructor(
    private val repository: LocalComicsRepository
) {
    operator fun invoke(comic: DResult) = repository.removeFavoriteComics(comic)
}