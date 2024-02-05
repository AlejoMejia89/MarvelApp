package com.alejandromejia.marvelapp.di

import android.app.Application
import com.alejandromejia.marvelapp.data.remote.api.AppService
import com.alejandromejia.marvelapp.data.remote.repository.characterdetail.RemoteCharacterDetailRepository
import com.alejandromejia.marvelapp.data.remote.repository.characters_list.RemoteCharactersListRepository
import com.alejandromejia.marvelapp.data.remote.repository.comics.RemoteComicRepository
import com.alejandromejia.marvelapp.data.remote.repository.comics.RemoteComicsListRepository
import com.alejandromejia.marvelapp.data.remote.repository.search.RemoteDataSearchRepository
import com.alejandromejia.marvelapp.data.remote.utils.Constants
import com.alejandromejia.marvelapp.domain.repository.ICharacterDetailRepository
import com.alejandromejia.marvelapp.domain.repository.ICharactersListRepository
import com.alejandromejia.marvelapp.domain.repository.IComicRepository
import com.alejandromejia.marvelapp.domain.repository.IComicsListRepository
import com.alejandromejia.marvelapp.domain.repository.ISearchRepository
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppServicesModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
            .readTimeout(35, TimeUnit.SECONDS)
            .cache(cache)
        return httpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun providesGson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
        .create()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): AppService =
        retrofit.create(AppService::class.java)

    @Provides
    @Singleton
    fun providesRemoteCharactersListRepository(
        remoteCharactersListRepository: RemoteCharactersListRepository
    ): ICharactersListRepository = remoteCharactersListRepository

    @Provides
    @Singleton
    fun providesRemoteSearchRepository(
        remoteDataSearchRepository: RemoteDataSearchRepository
    ): ISearchRepository = remoteDataSearchRepository

    @Provides
    @Singleton
    fun providesRemoteCharacterDetailRepository(
        remoteCharacterDetailRepository: RemoteCharacterDetailRepository
    ): ICharacterDetailRepository = remoteCharacterDetailRepository

    @Provides
    @Singleton
    fun providesRemoteComicsListRepository(
        remoteComicsListRepository: RemoteComicsListRepository
    ): IComicsListRepository = remoteComicsListRepository

    @Provides
    @Singleton
    fun providesRemoteComicRepository(
        remoteComicRepository: RemoteComicRepository
    ): IComicRepository = remoteComicRepository
}