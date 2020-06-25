package com.todaysquare.disamplecode_koin

import com.todaysquare.disamplecode_koin.ui.main.Friend
import com.todaysquare.disamplecode_koin.ui.main.SchoolCourse
import com.todaysquare.disamplecode_koin.ui.main.Student

import org.koin.dsl.module

val appModule = module {

    single { SchoolCourse() }

    factory { Friend() }

    factory { Student(get(), get()) }

}