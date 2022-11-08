package com.monksoft.examplemvvm.data.repository

import android.webkit.WebStorage.QuotaUpdater
import com.monksoft.examplemvvm.data.model.QuoteModel
import com.monksoft.examplemvvm.data.model.QuoteProvider
import com.monksoft.examplemvvm.data.network.QuoteService

class QuoteRepository {

    private val api = QuoteService()

    suspend fun getAllQuotes() : List<QuoteModel>{
        val response : List<QuoteModel> =  api.getQuotes()
        QuoteProvider.quotes = response
        return response
    }
}