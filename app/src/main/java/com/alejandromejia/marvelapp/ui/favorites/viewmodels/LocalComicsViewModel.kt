package com.alejandromejia.marvelapp.ui.favorites.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejandromejia.marvelapp.domain.models.comics.DResult
import com.alejandromejia.marvelapp.domain.usecases.local.GetFavoritesComicsUseCase
import com.alejandromejia.marvelapp.domain.usecases.local.RemoveFavoriteComicUseCase
import com.alejandromejia.marvelapp.domain.usecases.local.SaveFavoriteComicUseCase
import com.alejandromejia.marvelapp.ui.favorites.models.FavoritesComicsView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalComicsViewModel @Inject constructor(
    private val saveFavoriteComicUseCase: SaveFavoriteComicUseCase,
    private val removeFavoriteComicUseCase: RemoveFavoriteComicUseCase,
    private val getFavoritesComicsUseCase: GetFavoritesComicsUseCase
) : ViewModel() {

    private val _favoriteComics: MutableLiveData<FavoritesComicsView> =
        MutableLiveData<FavoritesComicsView>()
    val favoriteComics: LiveData<FavoritesComicsView> get() = _favoriteComics


    fun saveFavoriteComics(model: DResult) {
        viewModelScope.launch {
            saveFavoriteComicUseCase(model)
        }
    }

    fun removeFavorite(model: DResult) {
        viewModelScope.launch {
            removeFavoriteComicUseCase(model)
        }
    }

    fun getFavoritesComics() {
        viewModelScope.launch {
            _favoriteComics.value = FavoritesComicsView(
                charactersList = getFavoritesComicsUseCase(),
                loading = false
            )
        }
    }
}