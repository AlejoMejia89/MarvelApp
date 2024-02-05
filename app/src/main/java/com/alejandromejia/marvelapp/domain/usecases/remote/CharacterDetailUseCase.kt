package com.alejandromejia.marvelapp.domain.usecases.remote

import com.alejandromejia.marvelapp.data.remote.network.Either
import com.alejandromejia.marvelapp.data.remote.network.Failure
import com.alejandromejia.marvelapp.domain.models.characters.DCharacters
import com.alejandromejia.marvelapp.domain.repository.ICharacterDetailRepository
import javax.inject.Inject

class CharacterDetailUseCase @Inject constructor(
    private val repository: ICharacterDetailRepository
) {

    suspend operator fun invoke(id: Int): Either<Failure, DCharacters> {
        return repository.getCharacterById(id)
    }

}