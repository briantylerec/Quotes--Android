package com.monksoft.examplemvvm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.monksoft.examplemvvm.model.QuoteModel
import com.monksoft.examplemvvm.model.QuoteProvider

//live data permite a la activity suscribirse a un modelo de datos y se llame automaticamente cuando se realiza cambios en el modelo
class QuoteViewModel : ViewModel() {

    //encapsular el modelo en live data, metido en mutable porque el valor de QuoteModel va a ir cambiando
    val  quoteModel = MutableLiveData<QuoteModel>()

    fun randomQuote(){
        val currentQuote : QuoteModel = QuoteProvider.random()
        quoteModel.postValue(currentQuote)
    }
}