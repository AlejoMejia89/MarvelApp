package com.alejandromejia.marvelapp.domain.usecases.remote

import com.alejandromejia.marvelapp.data.remote.network.Either
import com.alejandromejia.marvelapp.data.remote.network.Failure
import com.alejandromejia.marvelapp.domain.models.characters.DCharacters
import com.alejandromejia.marvelapp.domain.repository.ICharactersListRepository
import javax.inject.Inject

class CharactersListUseCase @Inject constructor(
    private val repository: ICharactersListRepository
) {

    suspend operator fun invoke(offset: String): Either<Failure, DCharacters> {
        return repository.getCharactersList(offset)
    }
}