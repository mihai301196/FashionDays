package com.mihaiim.fashiondays.domain.models

interface ClothingItemModel {
    val id: String
}

data class ClothingModel(
    val productId: Long,
    val productBrand: String,
    val productName: String,
    val productImages: ClothingImagesModel,
) : ClothingItemModel {

    override val id: String
        get() = productId.toString()
}

data class TitleModel(val name: String) : ClothingItemModel {

    override val id: String
        get() = name
}

data class ClothingImagesModel(
    val thumbs: List<String>,
    val zooms: List<String>,
)