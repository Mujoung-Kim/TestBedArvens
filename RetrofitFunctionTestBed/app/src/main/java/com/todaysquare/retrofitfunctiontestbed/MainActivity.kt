package com.todaysquare.retrofitfunctiontestbed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.todaysquare.retrofitfunctiontestbed.data.network.GithubService
import com.todaysquare.retrofitfunctiontestbed.data.network.api.GitRestApi
import com.todaysquare.retrofitfunctiontestbed.data.repository.RepositoryItem

import com.todaysquare.retrofitfunctiontestbed.ui.adapter.CustomAdapter
import com.todaysquare.retrofitfunctiontestbed.utils.Constants.Url.Companion.BASE_GIT

import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
    ※ Retrofit 구성요소
     1. 네트워크 통신을 설정 및 관리하는 Retrofit body
     2. Api server 의 Http Method 정의하는 ServerInterface
     3. Request / Response
     4. DTO (Data Transfer Object)

*/
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mAdapter = CustomAdapter()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_GIT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        buttonRequest.setOnClickListener {
//            val gitRestApi = GitRestApi()
//
//            gitRestApi.startRetrofit()

            val githubService = retrofit.create(GithubService::class.java)

            githubService.users().enqueue(object : Callback<List<RepositoryItem>>{
                override fun onFailure(call: Call<List<RepositoryItem>>, t: Throwable) {

                }

                override fun onResponse(call: Call<List<RepositoryItem>>,
                    response: Response<List<RepositoryItem>>) {
                    mAdapter.userList.addAll(response.body() as List<RepositoryItem>)
                    mAdapter.notifyDataSetChanged()

                }
            })
        }
    }
}