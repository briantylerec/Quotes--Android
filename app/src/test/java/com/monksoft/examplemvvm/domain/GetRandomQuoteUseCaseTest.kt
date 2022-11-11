package com.monksoft.examplemvvm.domain

import com.monksoft.examplemvvm.data.repository.QuoteRepository
import com.monksoft.examplemvvm.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetRandomQuoteUseCaseTest {
    @RelaxedMockK
    private lateinit var repository: QuoteRepository

    lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    @Before
    fun setup(){
        MockKAnnotations.init(this)
        getRandomQuoteUseCase = GetRandomQuoteUseCase(repository)
    }

    @Test
    fun `when database is empty then return null`() = runBlocking{
        //given
        coEvery { repository.getAllQuotesFromDataBase() } returns emptyList()

        //when
        val response = getRandomQuoteUseCase()

        //then
        assertNull(response)
    }

    @Test
    fun `when database is not empty then return quote`() = runBlocking {
        //given
        val quoteList = listOf(Quote("Suscribete","Brian"))
        coEvery { repository.getAllQuotesFromDataBase() } returns quoteList

        //when
        val response = getRandomQuoteUseCase()

        //then
        assertEquals(response, quoteList.first())

    }
}