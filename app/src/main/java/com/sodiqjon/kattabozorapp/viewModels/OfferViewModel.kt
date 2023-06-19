package com.sodiqjon.kattabozorapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sodiqjon.kattabozorapp.common.Resource
import com.sodiqjon.kattabozorapp.common.UiEvent
import com.sodiqjon.kattabozorapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OfferViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _offerListObserver = MutableStateFlow<UiEvent>(UiEvent.Empty)
    val offerListObserver get() = _offerListObserver

    init {
        getOfferList()
    }

    fun getOfferList() {
        viewModelScope.launch {
            _offerListObserver.value = UiEvent.Loading
            when (val resource = repository.getAdviceCategories()) {
                is Resource.Success -> {
                    _offerListObserver.value = UiEvent.Success(resource.data)
                }

                is Resource.Error -> {
                    _offerListObserver.value = UiEvent.Error(resource.message)
                }
            }
        }
    }
}