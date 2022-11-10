package com.monksoft.examplemvvm.domain.model

import com.monksoft.examplemvvm.data.dataBase.entities.QuoteEntity
import com.monksoft.examplemvvm.data.model.QuoteModel

class Quote (
    val quote : String,
    val author : String
)

fun QuoteModel.toDomain() = Quote(quote, author)

fun QuoteEntity.toDomain() = Quote(quote, author)