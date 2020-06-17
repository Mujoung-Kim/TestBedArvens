package com.todaysquare.rxkotlintodo_retrofit2.api

import com.todaysquare.rxkotlintodo_retrofit2.api.apiresponse.BaseApiResponse
import com.todaysquare.rxkotlintodo_retrofit2.api.apiresponse.GetTodoListApiResponse
import com.todaysquare.rxkotlintodo_retrofit2.utils.Constants.ADD_TODO
import com.todaysquare.rxkotlintodo_retrofit2.utils.Constants.EDIT_TODO
import com.todaysquare.rxkotlintodo_retrofit2.utils.Constants.GET_TODO_LIST

import io.reactivex.Observable

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @POST(GET_TODO_LIST)
    fun getTodoList(): Observable<GetTodoListApiResponse>

    @FormUrlEncoded
    @POST(EDIT_TODO)
    fun editTodo(
        @Field("todo_id") todoID: String,
        @Field("todo") todo: String

    ): Observable<BaseApiResponse>

    @FormUrlEncoded
    @POST(ADD_TODO)
    fun addTodo(@Field("newtodo") todo: String): Observable<BaseApiResponse>

}