package com.alejandromejia.marvelapp.data.remote.repository.characters_list

import com.alejandromejia.marvelapp.data.remote.api.AppService
import com.alejandromejia.marvelapp.data.remote.network.Either
import com.alejandromejia.marvelapp.data.remote.network.Failure
import com.alejandromejia.marvelapp.data.remote.utils.tryCatch
import com.alejandromejia.marvelapp.domain.models.characters.DCharacters
import com.alejandromejia.marvelapp.domain.repository.ICharactersListRepository
import com.alejandromejia.marvelapp.utils.STATUS_MESSAGE
import org.json.JSONObject
import javax.inject.Inject

class RemoteCharactersListRepository @Inject constructor(
    private val appService: AppService
) : ICharactersListRepository {

    override suspend fun getCharactersList(offset: String): Either<Failure, DCharacters> {

        val res = appService.getAllCharacters(offset = offset)

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