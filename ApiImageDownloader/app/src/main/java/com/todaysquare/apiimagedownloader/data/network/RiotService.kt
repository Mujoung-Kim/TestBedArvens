package com.todaysquare.apiimagedownloader.data.network

import com.todaysquare.apiimagedownloader.data.network.response.ApiModels
import com.todaysquare.apiimagedownloader.data.network.response.Data
import com.todaysquare.apiimagedownloader.data.network.response.Skin
import com.todaysquare.apiimagedownloader.utils.Constants.Param.Companion.ID
import com.todaysquare.apiimagedownloader.utils.Constants.Param.Companion.NAME
import com.todaysquare.apiimagedownloader.utils.Constants.Param.Companion.NUM
import com.todaysquare.apiimagedownloader.utils.Constants.Url.Companion.GET_CHAMP
import com.todaysquare.apiimagedownloader.utils.Constants.Url.Companion.GET_IMAGE
import com.todaysquare.apiimagedownloader.utils.Constants.Url.Companion.GET_TEST
import com.todaysquare.apiimagedownloader.utils.Constants.Url.Companion.GET_TEST2

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path

interface RiotService {

    @GET(GET_CHAMP)
    fun getChampion(@Path(ID) id: String): Call<Data>

    @FormUrlEncoded
    @GET(GET_IMAGE)
    fun getImage(
        @Path(ID) id: String,
        @Path(NUM) num: Int,
        @Field(NAME) name: String
    ): Call<Skin>

    @GET(GET_TEST)
    fun getImage(): Call<Skin>

    @GET(GET_TEST2)
    fun getData(): Call<List<ApiModels>>

}