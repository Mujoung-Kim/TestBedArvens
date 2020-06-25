package com.todaysquare.disamplecode_koin.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer

import com.todaysquare.disamplecode_koin.R
import com.todaysquare.disamplecode_koin.data.InjectCountData
import com.todaysquare.disamplecode_koin.ui.example.ExampleViewModel

import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val TAG = this.javaClass.simpleName
    private val exampleViewModel: ExampleViewModel by viewModel()
    private val inject_0 by inject<InjectCountData>()
    private val inject_1 by inject<InjectCountData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val student = get<Student>()
        val student1 = get<Student>()

        student.beSmart()
        student1.beSmart()*/

        //  Koin-DI + MVVM
        /*  Koin viewModel 로 연결하면서 필요 없어짐.
        val androidViewModelFactory  = ViewModelProvider
            .AndroidViewModelFactory.getInstance(application)

        exampleViewModel = ViewModelProvider(this, androidViewModelFactory)[ExampleViewModel::class.java]*/
        exampleViewModel.exampleData.observe(this, Observer {
            Log.v(TAG, ">>> ${it.value}")

        })

        exampleViewModel.requestData()
        exampleViewModel.printHello()

        inject_0.printCount()
        inject_1.printCount()

    }
}
