package com.todaysquare.disamplecode_koin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.todaysquare.disamplecode_koin.ui.main.Student

import org.koin.android.ext.android.get

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val student = get<Student>()
        val student1 = get<Student>()

        student.beSmart()
        student1.beSmart()

    }
}
