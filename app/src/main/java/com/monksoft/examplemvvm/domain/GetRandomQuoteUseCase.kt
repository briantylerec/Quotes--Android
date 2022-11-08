package com.monksoft.examplemvvm.domain

import com.monksoft.examplemvvm.data.model.QuoteModel
import com.monksoft.examplemvvm.data.model.QuoteProvider
import com.monksoft.examplemvvm.data.repository.QuoteRepository

class GetRandomQuoteUseCase {

    private val repository = QuoteRepository()

    operator fun invoke(): QuoteModel? {
        val quotes : List<QuoteModel> = QuoteProvider.quotes

        if (quotes.isNullOrEmpty()){
            val rndNumber = quotes.indices.random()
            return quotes[rndNumber]
        }
        return null
    }
}