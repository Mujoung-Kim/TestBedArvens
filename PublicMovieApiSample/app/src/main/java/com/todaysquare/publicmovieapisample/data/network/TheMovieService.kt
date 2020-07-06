package com.todaysquare.publicmovieapisample.data.network

import com.todaysquare.publicmovieapisample.data.network.responses.MovieListResponse
import com.todaysquare.publicmovieapisample.utils.Constants.Url.Companion.GET_DISCOVER

import kotlinx.coroutines.Deferred

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface TheMovieService {

    /**
     * REST 요청을 처리하기 위한 메서드
     * @param param QueryMap 을 통해 질의한 쿼리문을 Map 으로 부터 받는다.
     * @return Call<T> 콜백 인터페이스 반환, T는 주고 받을 데이터 구조
     * @QueryMap 어노테이션은 위치가 바뀌어도 동적으로 값을 받아올 수 있게 한다.
     */
    @GET(GET_DISCOVER)
    fun getTop(
        @QueryMap param: Map<String, String>
    ): Call<MovieListResponse>

    /**
     * 코틀린을 사용하는 버전의 요청 메서드
     * @return Deferred<T> 코루틴의 지연된 콜백 인터페이스 반환, T는 주고 받을 데이터 구조
     */
    @GET(GET_DISCOVER)
    fun getDeferredTop(
        @QueryMap param: Map<String, String>
    ): Deferred<MovieListResponse>

}