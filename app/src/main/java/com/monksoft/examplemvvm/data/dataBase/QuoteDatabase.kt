package com.monksoft.examplemvvm.data.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.monksoft.examplemvvm.data.dataBase.dao.QuoteDao
import com.monksoft.examplemvvm.data.dataBase.entities.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun getQuoteDao() : QuoteDao
}