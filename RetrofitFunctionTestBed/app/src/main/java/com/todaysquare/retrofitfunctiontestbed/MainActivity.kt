package com.todaysquare.retrofitfunctiontestbed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

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
    }
}