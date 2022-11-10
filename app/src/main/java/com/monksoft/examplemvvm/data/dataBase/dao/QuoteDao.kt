package com.monksoft.examplemvvm.data.dataBase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.monksoft.examplemvvm.data.dataBase.entities.QuoteEntity

@Dao
interface QuoteDao {

    @Query("select * from quote_table order by  author desc")
    suspend fun getAllQuotes() : List<QuoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes : List<QuoteEntity>)

    @Query("delete from quote_table")
    suspend fun deleteAllQuotes()
}