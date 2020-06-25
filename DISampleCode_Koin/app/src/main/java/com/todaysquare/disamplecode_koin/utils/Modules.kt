package com.todaysquare.disamplecode_koin.utils

import com.todaysquare.disamplecode_koin.data.InjectCountData
import com.todaysquare.disamplecode_koin.data.repositories.PackageRepository
import com.todaysquare.disamplecode_koin.ui.example.ExampleViewModel
import com.todaysquare.disamplecode_koin.ui.main.Friend
import com.todaysquare.disamplecode_koin.ui.main.SchoolCourse
import com.todaysquare.disamplecode_koin.ui.main.Student

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { SchoolCourse() }
    factory { Friend() }
    factory { Student(get(), get()) }

}

val exampleModule = module {
    single { PackageRepository(androidContext()) }
    single { PrintService(get()) }
    factory { InjectCountData() }

}

val koinViewModule = module { viewModel { ExampleViewModel(get()) } }