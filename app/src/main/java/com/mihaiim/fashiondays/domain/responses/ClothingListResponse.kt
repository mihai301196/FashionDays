package com.mihaiim.fashiondays.domain.responses

import com.google.gson.annotations.SerializedName

data class ClothingListResponse(
    @SerializedName("products") val clothing: List<ClothingResponse>,
)

data class ClothingResponse(
    @SerializedName("product_id") val productId: Long,
    @SerializedName("product_brand") val productBrand: String,
    @SerializedName("product_name") val productName: String,
    @SerializedName("product_images") val productImages: ClothingImagesResponse,
)

data class ClothingImagesResponse(
    @SerializedName("thumb") val thumbs: List<String>,
    @SerializedName("zoom") val zooms: List<String>,
)