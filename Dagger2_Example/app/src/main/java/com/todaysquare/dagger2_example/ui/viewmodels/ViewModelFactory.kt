package com.todaysquare.dagger2_example.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]

        creator ?: kotlin.run {
            for (entry in creators.entries)
                if (modelClass.isAssignableFrom(entry.key)) {
                    creator = entry.value
                    break
                }
        }

        creator ?: kotlin.run {
            throw IllegalArgumentException("No Suitable class found $modelClass")

        }

        try {
            @Suppress("UNCHECKED_CAST")

            return creator!!.get() as T

        } catch (error: Exception) {
            error.printStackTrace()
            throw RuntimeException("")

        }
    }
}