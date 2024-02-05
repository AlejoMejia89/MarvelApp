package com.alejandromejia.marvelapp.domain.usecases.remote

import com.alejandromejia.marvelapp.data.remote.network.Either
import com.alejandromejia.marvelapp.data.remote.network.Failure
import com.alejandromejia.marvelapp.domain.models.characters.DCharacters
import com.alejandromejia.marvelapp.domain.repository.ISearchRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: ISearchRepository
) {

    suspend operator fun invoke(text: String): Either<Failure, DCharacters> {
        return repository.getItemsSearch(text)
    }
}