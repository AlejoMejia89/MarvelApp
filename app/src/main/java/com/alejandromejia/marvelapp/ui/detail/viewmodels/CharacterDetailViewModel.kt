package com.alejandromejia.marvelapp.ui.detail.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejandromejia.marvelapp.data.remote.network.Failure
import com.alejandromejia.marvelapp.domain.models.characters.DCharacters
import com.alejandromejia.marvelapp.domain.models.comics.DComics
import com.alejandromejia.marvelapp.domain.usecases.remote.CharacterDetailUseCase
import com.alejandromejia.marvelapp.domain.usecases.remote.ComicsListUseCase
import com.alejandromejia.marvelapp.ui.detail.models.ComicsView
import com.alejandromejia.marvelapp.ui.home.models.CharactersView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterDetailUseCase: CharacterDetailUseCase,
    private val comicsListUseCase: ComicsListUseCase
) : ViewModel() {

    private val _characterView: MutableLiveData<CharactersView> =
        MutableLiveData<CharactersView>()
    val characterView: LiveData<CharactersView> get() = _characterView

    private val _comicsView: MutableLiveData<ComicsView> =
        MutableLiveData<ComicsView>()
    val comicsView: LiveData<ComicsView> get() = _comicsView

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun getCharacterById(id: Int) = viewModelScope.launch {
        _characterView.value = CharactersView(loading = true)

        val character = async { characterDetailUseCase(id) }.await()
        val comics = async { comicsListUseCase(id) }.await()

        character.fold(::handleFailure, ::handleSuccess)
        comics.fold(::handleFailure, ::handleDetailSuccess)
    }

    private fun handleDetailSuccess(dComics: DComics) {
        _comicsView.value = ComicsView(
            comicsList = dComics.data.results,
            loading = false,
            comics = dComics
        )
    }

    private fun handleSuccess(dCharacters: DCharacters) {
        _characterView.value = CharactersView(
            charactersList = dCharacters.data.results,
            loading = false,
            characters = dCharacters
        )
    }

    private fun handleFailure(failure: Failure) {
        _characterView.value =
            CharactersView(errorMessage = failure.toString(), loading = false)
        _errorMessage.value = failure.toString()
    }

}