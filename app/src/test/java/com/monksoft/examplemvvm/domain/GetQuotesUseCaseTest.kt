package com.monksoft.examplemvvm.domain

import com.monksoft.examplemvvm.data.dataBase.entities.toDataBase
import com.monksoft.examplemvvm.data.repository.QuoteRepository
import com.monksoft.examplemvvm.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class GetQuotesUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: QuoteRepository

    lateinit var getQuotesUseCase: GetQuotesUseCase

    @Before
    fun setup(){
        MockKAnnotations.init(this)
        getQuotesUseCase = GetQuotesUseCase(repository)
    }

    @Test
    //given when then
    fun `when the api doesnt return anything then get values from database`() = runBlocking {
        //given
        coEvery { repository.getAllQuotesFromApi() } returns emptyList()

        //when
        getQuotesUseCase()

        //then   exactly es para comparar que se ejecuto una sola vez
        coVerify ( exactly = 1 ) { repository.getAllQuotesFromDataBase() }
    }

    @Test
    //given when then
    fun `when the api return anything then get values from database`() = runBlocking {
        //given
        val myList = listOf(Quote("Suscribete","Brian"))
        coEvery { repository.getAllQuotesFromApi() } returns myList

        //when
        val response = getQuotesUseCase()

        //then   exactly es para comparar que se ejecuto una sola vez
        coVerify ( exactly = 1 ) { repository.clearQuotes() }
        coVerify ( exactly = 1 ) { repository.insertQuotes(any()) }

        //para comaprar que no se ha llamado
        coVerify ( exactly = 0 ) { repository.getAllQuotesFromDataBase() }

        assertEquals(myList, response)
    }
}