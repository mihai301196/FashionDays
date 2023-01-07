package com.mihaiim.fashiondays.domain.models

data class ClothingModel(
    val productId: Long,
    val productBrand: String,
    val productName: String,
    val productImages: ClothingImagesModel,
)

data class ClothingImagesModel(
    val thumbs: List<String>,
    val zooms: List<String>,
)