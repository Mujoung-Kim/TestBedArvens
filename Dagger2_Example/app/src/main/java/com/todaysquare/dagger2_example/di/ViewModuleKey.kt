package com.todaysquare.dagger2_example.di

import androidx.lifecycle.ViewModel

import dagger.MapKey

import kotlin.reflect.KClass

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@MapKey
annotation class ViewModuleKey (val value: KClass<out ViewModel>)