package com.alejandromejia.marvelapp.domain.usecases.remote

import com.alejandromejia.marvelapp.data.remote.network.Either
import com.alejandromejia.marvelapp.data.remote.network.Failure
import com.alejandromejia.marvelapp.domain.models.comics.DComics
import com.alejandromejia.marvelapp.domain.repository.IComicRepository
import javax.inject.Inject

class ComicUseCase @Inject constructor(
    private val repository: IComicRepository
) {

    suspend operator fun invoke(id: Int): Either<Failure, DComics> {
        return repository.getComicById(id)
    }

}