package com.alejandromejia.marvelapp.ui.comics.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejandromejia.marvelapp.data.remote.network.Failure
import com.alejandromejia.marvelapp.domain.models.comics.DComics
import com.alejandromejia.marvelapp.domain.models.comics.DResult
import com.alejandromejia.marvelapp.domain.usecases.local.GetFavoritesComicsUseCase
import com.alejandromejia.marvelapp.domain.usecases.remote.ComicUseCase
import com.alejandromejia.marvelapp.ui.detail.models.ComicsView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(
    private val comicUseCase: ComicUseCase,
    private val getFavoritesComicsUseCase: GetFavoritesComicsUseCase
) : ViewModel() {

    private val _comicView: MutableLiveData<ComicsView> =
        MutableLiveData<ComicsView>()
    val comicView: LiveData<ComicsView> get() = _comicView

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun getComicById(id: Int) {
        _comicView.value = ComicsView(loading = true)

        viewModelScope.launch {
            comicUseCase(id).fold(::handleFailure, ::handleSuccess)
        }
    }

    private fun handleSuccess(dCharacters: DComics) {
        val result = validateFavorites(dCharacters)

        _comicView.value = ComicsView(
            comicsList = result,
            loading = false,
            comics = dCharacters
        )
    }

    private fun handleFailure(failure: Failure) {
        _comicView.value =
            ComicsView(errorMessage = failure.toString(), loading = false)
        _errorMessage.value = failure.toString()
    }

    private fun validateFavorites(character: DComics): List<DResult> {
        val favoriteIds = getFavoritesComicsUseCase().map { it.id }

        return character.data.results.map { result ->
            result.copy(isFavorite = favoriteIds.contains(result.id))
        }
    }

}