package com.monksoft.examplemvvm.data.repository

import com.monksoft.examplemvvm.data.dataBase.dao.QuoteDao
import com.monksoft.examplemvvm.data.dataBase.entities.QuoteEntity
import com.monksoft.examplemvvm.data.model.QuoteModel
import com.monksoft.examplemvvm.data.network.QuoteService
import com.monksoft.examplemvvm.domain.model.Quote
import com.monksoft.examplemvvm.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api : QuoteService,
    private val quoteDao : QuoteDao) {

    suspend fun getAllQuotesFromApi() : List<Quote> = api.getQuotes().map{ it.toDomain() }

    suspend fun getAllQuotesFromDataBase() : List<Quote> = quoteDao.getAllQuotes().map { it.toDomain() }

    //se inserta QUoteEntity porque es a la BD
    suspend fun insertQuotes(quotes : List<QuoteEntity>) = quoteDao.insertAll(quotes)

    suspend fun clearQuotes() = quoteDao.deleteAllQuotes()
}