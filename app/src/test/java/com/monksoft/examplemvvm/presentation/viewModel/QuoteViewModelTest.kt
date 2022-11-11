package com.monksoft.examplemvvm.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.monksoft.examplemvvm.domain.GetQuotesUseCase
import com.monksoft.examplemvvm.domain.GetRandomQuoteUseCase
import com.monksoft.examplemvvm.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class QuoteViewModelTest {

    @RelaxedMockK
    private lateinit var getQuotesUseCase : GetQuotesUseCase

    @RelaxedMockK
    private lateinit var getRandomQuoteUseCase : GetRandomQuoteUseCase

    private lateinit var quoteViewModel: QuoteViewModel

    @get:Rule
    var rule : InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        quoteViewModel = QuoteViewModel(getQuotesUseCase, getRandomQuoteUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewModel is created at the first time, get all quotes and set the first value` () = runTest {
        //given
        val quoteList = listOf(Quote("Hola", "texto"), Quote("sus", "texto 2"))

        coEvery { getQuotesUseCase() } returns quoteList

        //when
        quoteViewModel.onCreate()

        //then
        assert(quoteViewModel.quoteModel.value == quoteList.first())
    }

    @Test
    fun `when randomUseCase return a quote set on the liveData`() =  runTest {
        //given
        val quote = Quote("Hola", "texto")
        coEvery { getRandomQuoteUseCase() } returns quote

        //when
        quoteViewModel.randomQuote()

        //then
        assert(quoteViewModel.quoteModel.value === quote)
    }

    @Test
    fun `if randomQuoteUseCase return null keep the last value`() = runTest {

        //given
        val quote = Quote("Hola", "texto")
        quoteViewModel.quoteModel.value = quote
        coEvery { getRandomQuoteUseCase() } returns null

        //when
        quoteViewModel.randomQuote()

        //then
        assert(quoteViewModel.quoteModel.value == quote)
    }
}