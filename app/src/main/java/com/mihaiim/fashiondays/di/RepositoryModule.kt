package com.mihaiim.fashiondays.di

import com.mihaiim.fashiondays.domain.FashionDaysApi
import com.mihaiim.fashiondays.domain.repositories.ClothingRepository
import com.mihaiim.fashiondays.domain.repositories.ClothingRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
        fashionDaysApi: FashionDaysApi,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): ClothingRepository = ClothingRepositoryImpl(fashionDaysApi, ioDispatcher)
}