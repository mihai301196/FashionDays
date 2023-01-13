package com.mihaiim.fashiondays.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mihaiim.fashiondays.domain.models.ClothingItemModel
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

    private val _clothingResource = MutableLiveData<Event<Resource<List<ClothingItemModel>>>>()
    val clothingResource: LiveData<Event<Resource<List<ClothingItemModel>>>> = _clothingResource

    private val _clothingList = MutableLiveData<List<ClothingItemModel>>()
    val clothingList: LiveData<List<ClothingItemModel>> = _clothingList

    fun getWomenClothing() {
        _clothingResource.value = Event(Resource.Loading)
        _clothingList.value = listOf()
        viewModelScope.launch {
            val response = clothingRepository.womenClothing()
            _clothingResource.value = Event(response)

            if (response is Resource.Success) {
                _clothingList.postValue(response.data)
            }
        }
    }

    fun removeClothing(productId: Long) {
        _clothingList.value = clothingList.value?.filter {
            (it as? ClothingModel)?.productId != productId
        }
    }
}