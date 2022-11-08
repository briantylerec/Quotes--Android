package com.monksoft.examplemvvm.domain

import com.monksoft.examplemvvm.data.model.QuoteModel
import com.monksoft.examplemvvm.data.repository.QuoteRepository

class GetQuotesUseCase {

    private val repository = QuoteRepository()

    suspend operator fun invoke() : List<QuoteModel> = repository.getAllQuotes()
}