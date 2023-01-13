package com.mihaiim.fashiondays.domain.repositories

import com.mihaiim.fashiondays.domain.models.ClothingItemModel
import com.mihaiim.fashiondays.others.Resource

interface ClothingRepository {

    suspend fun womenClothing(): Resource<List<ClothingItemModel>>
}