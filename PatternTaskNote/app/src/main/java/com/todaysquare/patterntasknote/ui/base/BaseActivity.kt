package com.todaysquare.patterntasknote.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

import io.reactivex.disposables.CompositeDisposable

/*
    1. 먼저 BaseActivity, BaseFragment, BaseViewModel 을 만들어 주었습니다.
        -> 데이터바인딩, 공통함수 등을 관리하기 쉽게 해줍니다.
    2. Koin 사용을 위한 Application 과 Module 을 정의해줍니다.
        -> 의존성 주입을 사용함으로써 결합도를 낮춰주고 유연성 및 확장성은 높여주어 개발도 편리하고,
           유지보수하기 쉽게 만들어주므로 적극 사용하도록 합니다.
    3. Utils 클래스들을 정의 합니다.
        -> (데이터바인딩어댑터 , 무한스크롤 클래스, 네트워크 상태확인을 넣어줬습니다.)
*/
abstract class BaseActivity<B : ViewDataBinding>(@LayoutRes val layoutID: Int) : AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()
    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutID)
        binding.lifecycleOwner = this

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()

    }

    protected fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    }
}