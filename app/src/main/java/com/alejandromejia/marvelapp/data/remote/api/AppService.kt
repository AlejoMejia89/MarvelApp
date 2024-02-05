package com.alejandromejia.marvelapp.data.remote.api

import com.alejandromejia.marvelapp.data.models.characters.Characters
import com.alejandromejia.marvelapp.data.models.comics.Comics
import com.alejandromejia.marvelapp.data.remote.utils.Constants
import com.alejandromejia.marvelapp.utils.GET_ALL_CHARACTERS
import com.alejandromejia.marvelapp.utils.GET_CHARACTER_BY_ID
import com.alejandromejia.marvelapp.utils.GET_CHARACTER_BY_NAME
import com.alejandromejia.marvelapp.utils.GET_COMICS_BY_CHARACTER_ID
import com.alejandromejia.marvelapp.utils.GET_COMICS_BY_ID
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface AppService {

    @GET(GET_ALL_CHARACTERS)
    suspend fun getAllCharacters(
        @Query(value = "limit") limit: String = Constants.limit,
        @Query(value = "offset") offset: String,
        @QueryMap commonQueryParams: Map<String, String> = Constants.commonQueryParams
    ): Response<Characters>

    @GET(GET_CHARACTER_BY_NAME)
    suspend fun getCharactersByName(
        @Query(value = "nameStartsWith") search: String,
        @QueryMap commonQueryParams: Map<String, String> = Constants.commonQueryParams
    ): Response<Characters>

    @GET(GET_CHARACTER_BY_ID)
    suspend fun getCharacterById(
        @Path(value = "characterId") characterId: Int,
        @QueryMap commonQueryParams: Map<String, String> = Constants.commonQueryParams
    ): Response<Characters>

    @GET(GET_COMICS_BY_CHARACTER_ID)
    suspend fun getComicsByCharacterId(
        @Path(value = "characterId") characterId: Int,
        @QueryMap commonQueryParams: Map<String, String> = Constants.commonQueryParams
    ): Response<Comics>

    @GET(GET_COMICS_BY_ID)
    suspend fun getComicById(
        @Path(value = "comicId") comicId: Int,
        @QueryMap commonQueryParams: Map<String, String> = Constants.commonQueryParams
    ): Response<Comics>

}