package com.todaysquare.patterntasknote.ui.business

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.todaysquare.patterntasknote.data.databases.entities.BusinessNotice
import com.todaysquare.patterntasknote.data.repositories.BusinessNoticeRepository
import com.todaysquare.patterntasknote.ui.base.BaseViewModel
import com.todaysquare.patterntasknote.utils.Constants.Param.Companion.TEST

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BusinessNoticeViewModel(private val repository: BusinessNoticeRepository) : BaseViewModel() {
    private val _noticeList = MutableLiveData<ArrayList<BusinessNotice>>()
    val noticeList: LiveData<ArrayList<BusinessNotice>> get() = _noticeList

    fun requestNotice() {
        compositeDisposable.add(repository.requestNotice()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showProgress() }
            .doAfterTerminate { hideProgress() }
            .subscribe({
                _noticeList.value = it as ArrayList<BusinessNotice>?

            }, {
                Log.d(javaClass.simpleName + TEST, "" + it)

            }))

    }

    fun requestMoreNotice(offset: Int) {
        compositeDisposable.add(repository.requestMoreNotice(offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showProgress() }
            .doAfterTerminate { hideProgress() }
            .subscribe({ notice ->
                val pagingNoticeList = _noticeList.value

                pagingNoticeList?.addAll(notice)
                _noticeList.value = pagingNoticeList

            }, {
                Log.d(javaClass.simpleName + TEST, "" + it)

            }))

    }
}