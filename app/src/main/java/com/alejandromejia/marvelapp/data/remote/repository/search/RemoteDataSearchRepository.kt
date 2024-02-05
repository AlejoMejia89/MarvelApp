package com.alejandromejia.marvelapp.data.remote.repository.search

import com.alejandromejia.marvelapp.data.remote.api.AppService
import com.alejandromejia.marvelapp.data.remote.network.Either
import com.alejandromejia.marvelapp.data.remote.network.Failure
import com.alejandromejia.marvelapp.data.remote.utils.tryCatch
import com.alejandromejia.marvelapp.domain.models.characters.DCharacters
import com.alejandromejia.marvelapp.domain.repository.ISearchRepository
import com.alejandromejia.marvelapp.utils.STATUS_MESSAGE
import org.json.JSONObject
import javax.inject.Inject

class RemoteDataSearchRepository @Inject constructor(
    private val appService: AppService
) : ISearchRepository {
    override suspend fun getItemsSearch(
        text: String
    ): Either<Failure, DCharacters> {

        return tryCatch {
            val res = appService.getCharactersByName(search = text)
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