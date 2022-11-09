package com.monksoft.examplemvvm.di

import com.monksoft.examplemvvm.common.Constants
import com.monksoft.examplemvvm.data.network.QuoteApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module //los modulos proveen dependencias
@InstallIn(SingletonComponent::class) //y se define el alcance
object NetworkModule {

    @Singleton //patron para unica instancia
    @Provides //es proveedor y va con esta etiqueta
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit : Retrofit) : QuoteApi {
        return retrofit.create((QuoteApi::class.java))
    }
}