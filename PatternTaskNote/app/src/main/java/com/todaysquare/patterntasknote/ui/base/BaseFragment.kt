package com.todaysquare.patterntasknote.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/*
        viewModel 을 제네릭으로 넘기는데 이 부분은 안넘기고 그냥 프래그먼트에서 해결해도 됩니다.
        Koin 사용 시 val viewModel: BusinessNoticeViewModel by viewModel()
        이런식으로 Base 말고 실제 프래그먼트에서 해주면 됩니다.
        실제 프래그먼트에서는 init()에 뷰를 초기화하는 작업을 override 해주도록 합니다.
*/
abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(@LayoutRes val layoutID: Int) : Fragment() {
    protected abstract val viewModel: VM
    protected lateinit var binding: B

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, layoutID, container, false)

        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        init()

    }

    abstract fun init()

    protected fun showToast(message: String) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

}