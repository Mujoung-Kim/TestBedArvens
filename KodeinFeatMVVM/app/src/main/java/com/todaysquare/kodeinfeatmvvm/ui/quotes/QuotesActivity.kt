package com.todaysquare.kodeinfeatmvvm.ui.quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.todaysquare.kodeinfeatmvvm.R
import com.todaysquare.kodeinfeatmvvm.data.databases.entites.Quote

import kotlinx.android.synthetic.main.activity_quotes.*

import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance

class QuotesActivity : AppCompatActivity(), DIAware {
    override val di by closestDI()
    private val viewModelFactory by instance<QuotesViewModelFactory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)

        initializeUi()

    }

    private fun initializeUi() {
        val viewModel =
            ViewModelProvider(this, viewModelFactory)[QuotesViewModel::class.java]

        viewModel.getQuotes().observe(this, Observer { quotes ->
            val stringBuilder = StringBuilder()

            quotes.forEach { quote -> stringBuilder.append("$quote\n\n") }

            textView_quotes.text = stringBuilder.toString()

        })

        button_add_quote.setOnClickListener {
            val quote = Quote(editText_quote.toString(), editText_author.toString())

            viewModel.addQuote(quote)
            editText_quote.setText("")
            editText_author.setText("")

        }
    }
}
