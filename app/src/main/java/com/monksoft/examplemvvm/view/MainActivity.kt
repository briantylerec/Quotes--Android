package com.monksoft.examplemvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.monksoft.examplemvvm.databinding.ActivityMainBinding
import com.monksoft.examplemvvm.viewModel.QuoteViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val quoteViewModel : QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quoteViewModel.quoteModel.observe(this, Observer { currentQuote ->
            with(binding) {
                tvQuote.text  = currentQuote.quote
                tvAuthor.text  = currentQuote.author
            }
        })

        binding.viewContainer.setOnClickListener { quoteViewModel.randomQuote() }
    }
}