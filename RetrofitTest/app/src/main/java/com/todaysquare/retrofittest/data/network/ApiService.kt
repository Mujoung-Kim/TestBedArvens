package com.todaysquare.retrofittest.data.network

import com.todaysquare.retrofittest.data.network.responses.TokenResponse
import com.todaysquare.retrofittest.utils.ToConstant
import com.todaysquare.retrofittest.utils.ToConstant.Param.Companion.CODE
import com.todaysquare.retrofittest.utils.ToConstant.Param.Companion.MESSAGE
import com.todaysquare.retrofittest.utils.ToConstant.Param.Companion.RESPONSE
import com.todaysquare.retrofittest.utils.ToConstant.Url.Companion.BASE_URL
import com.todaysquare.retrofittest.utils.ToConstant.Url.Companion.POST_GET_TOKEN
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ApiService {
    companion object {
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): ApiService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor).build()

            return Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build().create(ApiService::class.java)

        }
    }

    @FormUrlEncoded
    @POST(POST_GET_TOKEN)
    fun getToken(
        @Field(CODE) code: Int,
        @Field(MESSAGE) message: String,
        @Field(RESPONSE) response: TokenResponse
    ): Response<TokenResponse>


    @Multipart
    @POST(ToConstant.Url.POST_PROFILE_IMAGE_UPLOAD)
    fun getUpdateProfileInfo(
        @Part file: MultipartBody.Part?,
        @PartMap info: HashMap<String?, RequestBody?>?
    ): Call<String?>?


    @GET(ToConstant.Url.GET_ACTIVITY_EXERCISE_SEARCH)
    fun getExerciseSearchList(@Query("searchWord") searchWord: String?): Call<String?>

    @POST(ToConstant.Url.POST_ACTIVITY_EXERCISE_RECORD_INPUT)
    fun inputExerciseRecord(@Body model: RequestBody?): Call<String?>

}