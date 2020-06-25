package com.todaysquare.kodeindemocoffemaker

import com.todaysquare.kodeindemocoffemaker.di.CommonLogger

class AndroidLogger : CommonLogger {
    var text: String = ""
        private set
    var callback: (() -> Unit)? = null

    override fun log(message: String) {
        text += "$message\n"
        callback?.invoke()

    }
}