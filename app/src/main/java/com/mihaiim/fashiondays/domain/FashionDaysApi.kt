package com.mihaiim.fashiondays.domain

import com.mihaiim.fashiondays.domain.responses.ClothingListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface FashionDaysApi {

    @Headers("x-mobile-app-locale: ro_RO")
    @GET("8/g/women/clothing/")
    suspend fun womenClothing(): Response<ClothingListResponse>
}