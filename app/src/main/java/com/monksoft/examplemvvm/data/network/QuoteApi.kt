package com.monksoft.examplemvvm.data.network

import com.monksoft.examplemvvm.data.model.QuoteModel
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApi {

    @GET("/.json")
    suspend fun getAllQuotes(): Response<List<QuoteModel>>
}