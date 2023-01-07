package com.mihaiim.fashiondays.domain.repositories

import com.mihaiim.fashiondays.domain.FashionDaysApi
import com.mihaiim.fashiondays.domain.models.ClothingModel
import com.mihaiim.fashiondays.domain.models.ClothingImagesModel
import com.mihaiim.fashiondays.others.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ClothingRepositoryImpl @Inject constructor(
    private val fashionDaysApi: FashionDaysApi,
    private val ioDispatcher: CoroutineDispatcher,
) : ClothingRepository {

    override suspend fun womenClothing(): Resource<List<ClothingModel>> = withContext(ioDispatcher) {
        try {
            val response = fashionDaysApi.womenClothing()
            if(response.isSuccessful) {
                response.body()?.let { data ->
                    return@let Resource.Success(data.clothing.map { clothing ->
                        ClothingModel(
                            productId = clothing.productId,
                            productBrand = clothing.productBrand,
                            productName = clothing.productName,
                            productImages = ClothingImagesModel(
                                thumbs = clothing.productImages.thumbs,
                                zooms = clothing.productImages.zooms,
                            ),
                        )
                    })
                } ?: Resource.Error("An error occurred")
            } else {
                Resource.Error("An error occurred")
            }
        } catch (e: Exception) {
            Resource.Error("An error occurred")
        }
    }
}