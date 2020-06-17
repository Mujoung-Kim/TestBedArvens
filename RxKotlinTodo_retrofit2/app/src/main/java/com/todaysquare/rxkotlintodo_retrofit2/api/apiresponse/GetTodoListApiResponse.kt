package com.todaysquare.rxkotlintodo_retrofit2.api.apiresponse

import com.todaysquare.rxkotlintodo_retrofit2.datamodels.TodoModel

open class GetTodoListApiResponse(
    error_code: Int,
    error_message: String,
    val data: ArrayList<TodoModel>

) : BaseApiResponse(error_code, error_message)