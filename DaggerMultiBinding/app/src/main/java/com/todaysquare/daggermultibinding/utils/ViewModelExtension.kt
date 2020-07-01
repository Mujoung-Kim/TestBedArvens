package com.todaysquare.daggermultibinding.utils

import androidx.lifecycle.ViewModel

import io.reactivex.disposables.Disposable

fun ViewModel.addToDisposable(disposable: Disposable) {
    val disposableManager = DisposableManager()

    disposableManager.add(disposable)

}

fun ViewModel.removeAllDisposables() {
    val disposableManager = DisposableManager()

    disposableManager.dispose()

}