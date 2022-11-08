package com.monksoft.examplemvvm.data.network

import com.monksoft.examplemvvm.core.RetrofitHelper
import com.monksoft.examplemvvm.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class QuoteService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getQuotes(): List<QuoteModel>{

        //se procesa con corrutinas en un hilo secundario que no es el principal (MAIN), sino
        //en el IO para base de datos o servicios, y no satura el principal
        return withContext(Dispatchers.IO) {

            val response: Response<List<QuoteModel>> =
                retrofit.create(QuoteApi::class.java).getAllQuotes()
            response.body() ?: emptyList()
        }
    }
}