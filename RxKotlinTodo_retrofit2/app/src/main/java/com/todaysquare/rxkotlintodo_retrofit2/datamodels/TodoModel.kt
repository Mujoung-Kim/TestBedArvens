package com.todaysquare.rxkotlintodo_retrofit2.datamodels

import java.io.Serializable

data class TodoModel(
    val id: Int,
    var todoDescription: String,
    var todoTargetDate: String,
    var status: String

) : Serializable