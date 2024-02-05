package com.alejandromejia.marvelapp.data.local.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alejandromejia.marvelapp.data.local.dao.MarvelDao
import com.alejandromejia.marvelapp.data.local.models.ComicsFavoritesEntity
import com.alejandromejia.marvelapp.data.local.models.FavoritesEntity
import com.alejandromejia.marvelapp.data.local.models.RecentSearchEntity
import com.alejandromejia.marvelapp.utils.DB_NAME

@Database(
    entities = [FavoritesEntity::class, ComicsFavoritesEntity::class, RecentSearchEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun get(application: Application): AppDatabase {
            return Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                DB_NAME
            ).fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
    }

    abstract fun marvelDao(): MarvelDao

}