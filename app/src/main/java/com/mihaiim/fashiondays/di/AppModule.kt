package com.mihaiim.fashiondays.di

import android.content.Context
import android.graphics.drawable.ColorDrawable
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mihaiim.fashiondays.R
import com.mihaiim.fashiondays.domain.FashionDaysApi
import com.mihaiim.fashiondays.others.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFashionDaysApi(): FashionDaysApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(FashionDaysApi::class.java)
    }

    @Singleton
    @Provides
    fun provideGlide(
        @ApplicationContext context: Context,
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(ColorDrawable(ResourcesCompat.getColor(
                context.resources,
                R.color.black_85,
                null,
            )))
            .error(ColorDrawable(ResourcesCompat.getColor(
                context.resources,
                R.color.black_85,
                null,
            )))
    )
}