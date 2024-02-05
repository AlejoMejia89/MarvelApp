package com.alejandromejia.marvelapp.domain.repository

import com.alejandromejia.marvelapp.data.remote.network.Either
import com.alejandromejia.marvelapp.data.remote.network.Failure
import com.alejandromejia.marvelapp.domain.models.comics.DComics

interface IComicRepository {

    suspend fun getComicById(id: Int): Either<Failure, DComics>

}