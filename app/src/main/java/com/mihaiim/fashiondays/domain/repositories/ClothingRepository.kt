package com.mihaiim.fashiondays.domain.repositories

import com.mihaiim.fashiondays.domain.models.ClothingModel
import com.mihaiim.fashiondays.others.Resource

interface ClothingRepository {

    suspend fun womenClothing(): Resource<List<ClothingModel>>
}