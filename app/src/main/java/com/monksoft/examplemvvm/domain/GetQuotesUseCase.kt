package com.monksoft.examplemvvm.domain

import com.monksoft.examplemvvm.data.model.QuoteModel
import com.monksoft.examplemvvm.data.repository.QuoteRepository
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(private val repository : QuoteRepository) {

    suspend operator fun invoke() : List<QuoteModel> = repository.getAllQuotes()
}