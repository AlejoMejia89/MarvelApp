package com.alejandromejia.marvelapp.di

import android.content.Context
import androidx.room.Room
import com.alejandromejia.marvelapp.data.local.dao.MarvelDao
import com.alejandromejia.marvelapp.data.local.database.AppDatabase
import com.alejandromejia.marvelapp.data.local.repository.LocalComicsRepository
import com.alejandromejia.marvelapp.data.local.repository.LocalMarvelRepository
import com.alejandromejia.marvelapp.data.local.repository.LocalRecentSearchRepository
import com.alejandromejia.marvelapp.utils.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun providesMarvelDao(db: AppDatabase) = db.marvelDao()

    @Provides
    @Singleton
    fun providesLocalMarvelRepository(
        marvelDao: MarvelDao
    ): LocalMarvelRepository {
        return LocalMarvelRepository(marvelDao)
    }

    @Provides
    @Singleton
    fun providesLocalComicsRepository(
        marvelDao: MarvelDao
    ): LocalComicsRepository {
        return LocalComicsRepository(marvelDao)
    }

    @Provides
    @Singleton
    fun providesRecentSearchRepository(
        marvelDao: MarvelDao
    ): LocalRecentSearchRepository {
        return LocalRecentSearchRepository(marvelDao)
    }
}