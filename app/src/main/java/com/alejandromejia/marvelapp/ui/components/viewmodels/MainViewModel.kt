package com.alejandromejia.marvelapp.ui.components.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alejandromejia.marvelapp.ui.components.models.ComponentsView
import com.alejandromejia.marvelapp.ui.detail.models.ComponentsDetailView
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _componentView: MutableLiveData<ComponentsView> =
        MutableLiveData<ComponentsView>()
    val componentView: LiveData<ComponentsView> get() = _componentView

    private val _componentDetailView: MutableLiveData<ComponentsDetailView> =
        MutableLiveData<ComponentsDetailView>()
    val componentDetailView: LiveData<ComponentsDetailView> get() = _componentDetailView

    private val _lastScreen: MutableLiveData<ComponentsView> =
        MutableLiveData<ComponentsView>()
    val lastScreen: LiveData<ComponentsView> get() = _lastScreen

    private val _showBottomSheet: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>()
    val showBottomSheet: LiveData<Boolean> get() = _showBottomSheet

    fun navigateToScreen(componentsView: ComponentsView) {
        _componentView.value = componentsView
    }

    fun navigateToDetailScreen(componentsDetailView: ComponentsDetailView) {
        _componentDetailView.value = componentsDetailView
    }

    fun lastScreen(componentsView: ComponentsView){
        _lastScreen.value = componentsView
    }

    fun showBottomSheet(isVisible: Boolean) {
        _showBottomSheet.value = isVisible
    }
}