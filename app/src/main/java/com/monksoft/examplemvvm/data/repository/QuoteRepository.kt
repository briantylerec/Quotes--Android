package com.monksoft.examplemvvm.data.repository

import com.monksoft.examplemvvm.data.model.QuoteModel
import com.monksoft.examplemvvm.data.model.QuoteProvider
import com.monksoft.examplemvvm.data.network.QuoteService
import javax.inject.Inject

class QuoteRepository @Inject constructor(private val api : QuoteService, private val quoteProvider : QuoteProvider) {

    suspend fun getAllQuotes() : List<QuoteModel>{
        val response = api.getQuotes()
        quoteProvider.quotes = response
        return response
    }
}