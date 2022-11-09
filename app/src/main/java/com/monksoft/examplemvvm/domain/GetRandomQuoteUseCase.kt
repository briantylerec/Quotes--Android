package com.monksoft.examplemvvm.domain

import com.monksoft.examplemvvm.data.model.QuoteModel
import com.monksoft.examplemvvm.data.model.QuoteProvider
import com.monksoft.examplemvvm.data.repository.QuoteRepository
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(private val quoteProvider : QuoteProvider) {

    operator fun invoke(): QuoteModel? {
        val quotes : List<QuoteModel> = quoteProvider.quotes

        if (!quotes.isNullOrEmpty()){
            val rndNumber = quotes.indices.random()
            return quotes[rndNumber]
        }
        return null
    }
}