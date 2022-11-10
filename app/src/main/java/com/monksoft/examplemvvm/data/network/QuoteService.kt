package com.monksoft.examplemvvm.data.network

import com.monksoft.examplemvvm.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuoteService @Inject constructor(private val api : QuoteApi) {

    suspend fun getQuotes(): List<QuoteModel>{
        //se procesa con corrutinas en un hilo secundario que no es el principal (MAIN), sino
        //en el IO para base de datos o servicios, y no satura el principal
        return withContext(Dispatchers.IO) {
            val response = api.getAllQuotes()
            response.body() ?: emptyList()
        }
    }
}