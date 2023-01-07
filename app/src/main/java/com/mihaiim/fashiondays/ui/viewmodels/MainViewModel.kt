package com.mihaiim.fashiondays.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mihaiim.fashiondays.domain.models.ClothingModel
import com.mihaiim.fashiondays.domain.repositories.ClothingRepository
import com.mihaiim.fashiondays.others.Event
import com.mihaiim.fashiondays.others.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val clothingRepository: ClothingRepository
): ViewModel() {

    private val _clothingResource = MutableLiveData<Event<Resource<List<ClothingModel>>>>()
    val clothingResource: LiveData<Event<Resource<List<ClothingModel>>>> = _clothingResource

    private val _clothingList = MutableLiveData<List<ClothingModel>>()
    val clothingList: LiveData<List<ClothingModel>> = _clothingList

    fun getWomenClothing() {
        _clothingResource.value = Event(Resource.Loading)
        _clothingList.value = listOf()
        viewModelScope.launch {
            val response = clothingRepository.womenClothing()
            _clothingResource.value = Event(response)

            if (response is Resource.Success) {
                _clothingList.value = response.data
            }
        }
    }

    fun removeClothing(productId: Long) {
        _clothingList.value = clothingList.value?.filter { it.productId != productId }
    }
}