package com.monksoft.examplemvvm.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.monksoft.examplemvvm.databinding.ActivityMainBinding
import com.monksoft.examplemvvm.presentation.viewModel.QuoteViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val quoteViewModel : QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quoteViewModel.onCreate()

        quoteViewModel.quoteModel.observe(this, Observer { currentQuote ->
            with(binding) {
                tvQuote.text  = currentQuote.quote
                tvAuthor.text  = currentQuote.author
            }
        })

        quoteViewModel.isLoading.observe(this, Observer { status ->
            binding.progress.isVisible = status
        })

        setOnClickListenerFunctions()
    }

    private fun setOnClickListenerFunctions(){
        binding.viewContainer.setOnClickListener { quoteViewModel.randomQuote() }
    }
}