package com.alejandromejia.marvelapp.ui.home.viemodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejandromejia.marvelapp.data.remote.network.Failure
import com.alejandromejia.marvelapp.domain.models.characters.DCharacters
import com.alejandromejia.marvelapp.domain.models.characters.DResult
import com.alejandromejia.marvelapp.domain.usecases.local.GetFavoritesUseCase
import com.alejandromejia.marvelapp.domain.usecases.remote.CharacterDetailUseCase
import com.alejandromejia.marvelapp.domain.usecases.remote.CharactersListUseCase
import com.alejandromejia.marvelapp.ui.home.models.CharactersView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersListUseCase: CharactersListUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val characterDetailUseCase: CharacterDetailUseCase
) : ViewModel() {

    private val mutableCharacterList = mutableListOf<DResult>()

    private val _charactersView: MutableLiveData<CharactersView> =
        MutableLiveData<CharactersView>()
    val charactersView: LiveData<CharactersView> get() = _charactersView

    private val _charactersView1: MutableLiveData<List<DResult>> =
        MutableLiveData<List<DResult>>()
    val charactersView1: LiveData<List<DResult>> get() = _charactersView1


    private val _page: MutableLiveData<Int> =
        MutableLiveData<Int>()
    val page: LiveData<Int> get() = _page

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun getAllCharacters(page: String) {
        viewModelScope.launch {
            _charactersView.value = CharactersView(loading = true)
            charactersListUseCase(page).fold(::handleFailure, ::handleSuccess)
        }
    }

    private fun handleSuccess(dCharacters: DCharacters) {
        val result = validateFavorites(dCharacters)
        _charactersView.value = CharactersView(
            charactersList = result,
            characters = dCharacters,
            loading = false
        )
    }

    private fun handleFailure(failure: Failure) {
        _charactersView.value =
            CharactersView(errorMessage = failure.toString(), loading = false)
        _errorMessage.value = failure.toString()
    }

    private fun validateFavorites(character: DCharacters): List<DResult> {
        val favoriteList = getFavoritesUseCase()
        val ids = favoriteList.map { it.id }

        character.data.results.map {
            it.isFavorite = ids.contains(it.id)
        }

        return character.data.results
    }

    fun nextPage(page: Int) {
        _page.value = page
    }

    fun getCharacterById() = viewModelScope.launch {
        _charactersView.value = CharactersView(loading = true)

        val characters = listOf(
            1009220, // Thor
            1009351, // Hulk
            1009368, // Iron Man
            1009664  // Captain America
        )

        characters.map {
            async { characterDetailUseCase(it) }
        }.awaitAll().forEach { dCharacters ->
            dCharacters.fold(::handleFailure, ::handlePopularSuccess)
        }
    }

    private fun handlePopularSuccess(dCharacters: DCharacters) {
        val result = dCharacters.data.results.first()
        _charactersView1.value = (_charactersView1.value ?: emptyList()).plus(result).distinct()
    }


}