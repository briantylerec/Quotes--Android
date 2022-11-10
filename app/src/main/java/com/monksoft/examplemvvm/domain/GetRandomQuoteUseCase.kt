package com.monksoft.examplemvvm.domain

import com.monksoft.examplemvvm.data.repository.QuoteRepository
import com.monksoft.examplemvvm.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(private val repository: QuoteRepository) {

    suspend operator fun invoke(): Quote? {
        val quotes = repository.getAllQuotesFromDataBase()

        if (!quotes.isNullOrEmpty()){
            val rndNumber = quotes.indices.random()
            return quotes[rndNumber]
        }
        return null
    }
}