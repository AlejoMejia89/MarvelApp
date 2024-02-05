package com.alejandromejia.marvelapp.data.remote.repository.comics

import com.alejandromejia.marvelapp.data.remote.api.AppService
import com.alejandromejia.marvelapp.data.remote.network.Either
import com.alejandromejia.marvelapp.data.remote.network.Failure
import com.alejandromejia.marvelapp.data.remote.utils.tryCatch
import com.alejandromejia.marvelapp.domain.models.comics.DComics
import com.alejandromejia.marvelapp.domain.repository.IComicsListRepository
import com.alejandromejia.marvelapp.utils.STATUS_MESSAGE
import org.json.JSONObject
import javax.inject.Inject

class RemoteComicsListRepository @Inject constructor(
    private val appService: AppService
) : IComicsListRepository {

    override suspend fun getComicsByCharacterId(id: Int): Either<Failure, DComics> {

        val res = appService.getComicsByCharacterId(characterId = id)

        return tryCatch {
            when {
                res.isSuccessful -> {

                    res.body()?.let { it ->
                        Either.Right(it.toDomainObject())
                    } ?: Either.Left(Failure.DataError)
                }

                else -> {
                    res.errorBody()?.let { errorBody ->
                        val jsonObj = JSONObject(errorBody.charStream().readText())
                        val message =
                            if (jsonObj.has(STATUS_MESSAGE)) jsonObj.getString(STATUS_MESSAGE) else jsonObj.toString()
                        Either.Left(Failure.FeatureFailure(message))
                    } ?: Either.Left(Failure.ServerError)
                }
            }
        }
    }
}