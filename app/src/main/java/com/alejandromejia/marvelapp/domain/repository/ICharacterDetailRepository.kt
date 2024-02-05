package com.alejandromejia.marvelapp.domain.repository

import com.alejandromejia.marvelapp.data.remote.network.Either
import com.alejandromejia.marvelapp.data.remote.network.Failure
import com.alejandromejia.marvelapp.domain.models.characters.DCharacters

interface ICharacterDetailRepository {

    suspend fun getCharacterById(id: Int): Either<Failure, DCharacters>

}