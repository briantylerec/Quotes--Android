package com.monksoft.examplemvvm.domain

import com.monksoft.examplemvvm.data.dataBase.entities.toDataBase
import com.monksoft.examplemvvm.data.model.QuoteModel
import com.monksoft.examplemvvm.data.repository.QuoteRepository
import com.monksoft.examplemvvm.domain.model.Quote
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(private val repository : QuoteRepository) {

    suspend operator fun invoke() : List<Quote> {
        val quotes = repository.getAllQuotesFromApi()

         return if(!quotes.isNullOrEmpty()){
            repository.clearQuotes()
            repository.insertQuotes(quotes.map { it.toDataBase()})
             quotes
        } else {
            return repository.getAllQuotesFromDataBase()
        }
    }
}