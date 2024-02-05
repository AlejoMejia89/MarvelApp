package com.alejandromejia.marvelapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alejandromejia.marvelapp.data.local.models.ComicsFavoritesEntity
import com.alejandromejia.marvelapp.data.local.models.FavoritesEntity
import com.alejandromejia.marvelapp.data.local.models.RecentSearchEntity

@Dao
interface MarvelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(entity: FavoritesEntity)

    @Delete
    fun removeFavorite(entity: FavoritesEntity)

    @Query("SELECT * FROM FavoritesMarvelEntity ORDER BY name ASC")
    fun getFavoritesCharacters(): List<FavoritesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteComic(entity: ComicsFavoritesEntity)

    @Delete
    fun removeFavoriteComic(entity: ComicsFavoritesEntity)

    @Query("SELECT * FROM FavoritesComicsEntity ORDER BY name ASC")
    fun getFavoritesComics(): List<ComicsFavoritesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecentSearch(entity: RecentSearchEntity)

    @Delete
    fun removeRecentSearch(entity: RecentSearchEntity)

    @Query("SELECT DISTINCT * FROM RecentSearchEntity ORDER BY id DESC LIMIT 15")
    fun getRecentSearch(): List<RecentSearchEntity>


}