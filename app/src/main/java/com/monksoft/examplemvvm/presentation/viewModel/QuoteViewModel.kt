package com.monksoft.examplemvvm.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monksoft.examplemvvm.domain.GetQuotesUseCase
import com.monksoft.examplemvvm.domain.GetRandomQuoteUseCase
import com.monksoft.examplemvvm.domain.model.Quote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//live data permite a la activity suscribirse a un modelo de datos y se llame automaticamente cuando se realiza cambios en el modelo
@HiltViewModel
class QuoteViewModel @Inject constructor (
    private val getQuotesUseCase : GetQuotesUseCase,
    private val getRandomQuoteUseCase : GetRandomQuoteUseCase) : ViewModel() {

    //encapsular el modelo en live data, metido en mutable porque el valor de QuoteModel va a ir cambiando
    val quoteModel = MutableLiveData<Quote>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getQuotesUseCase()

            if(!result.isNullOrEmpty()) {
                quoteModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    fun randomQuote(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val quote = getRandomQuoteUseCase()
            if (quote != null) quoteModel.postValue(quote)//para como error pero esta bien
            isLoading.postValue(false)
        }
    }
}