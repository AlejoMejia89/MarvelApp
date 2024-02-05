package com.alejandromejia.marvelapp.domain.usecases.remote

import com.alejandromejia.marvelapp.data.remote.network.Either
import com.alejandromejia.marvelapp.data.remote.network.Failure
import com.alejandromejia.marvelapp.domain.models.comics.DComics
import com.alejandromejia.marvelapp.domain.repository.IComicsListRepository
import javax.inject.Inject

class ComicsListUseCase @Inject constructor(
    private val repository: IComicsListRepository
) {

    suspend operator fun invoke(id: Int): Either<Failure, DComics> {
        return repository.getComicsByCharacterId(id)
    }

}