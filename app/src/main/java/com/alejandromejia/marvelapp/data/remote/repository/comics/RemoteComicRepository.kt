package com.alejandromejia.marvelapp.data.remote.repository.comics

import com.alejandromejia.marvelapp.data.remote.api.AppService
import com.alejandromejia.marvelapp.data.remote.network.Either
import com.alejandromejia.marvelapp.data.remote.network.Failure
import com.alejandromejia.marvelapp.data.remote.utils.tryCatch
import com.alejandromejia.marvelapp.domain.models.comics.DComics
import com.alejandromejia.marvelapp.domain.repository.IComicRepository
import com.alejandromejia.marvelapp.utils.STATUS_MESSAGE
import org.json.JSONObject
import javax.inject.Inject

class RemoteComicRepository @Inject constructor(
    private val appService: AppService
) : IComicRepository {

    override suspend fun getComicById(id: Int): Either<Failure, DComics> {

        val res = appService.getComicById(comicId = id)

        return tryCatch {
            when {
                res.isSuccessful -> {

                    res.body()?.let {
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