package com.todaysquare.patterntasknote.ui.employ

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.todaysquare.patterntasknote.data.databases.entities.EmployNotice
import com.todaysquare.patterntasknote.data.repositories.EmployNoticeRepository
import com.todaysquare.patterntasknote.ui.base.BaseViewModel
import com.todaysquare.patterntasknote.utils.Constants.Param.Companion.TEST

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EmployNoticeViewModel(private val repository: EmployNoticeRepository) : BaseViewModel() {
    private val _noticeList = MutableLiveData<ArrayList<EmployNotice>>()
    val noticeList: LiveData<ArrayList<EmployNotice>> get() = _noticeList

    fun requestNotice() {
        compositeDisposable.add(repository.requestNotice()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showProgress() }
            .doAfterTerminate { hideProgress() }
            .subscribe({
                _noticeList.value = it as ArrayList<EmployNotice>?

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
            .subscribe({ notices ->
                val pagingNoticeList = _noticeList.value

                pagingNoticeList?.addAll(notices)
                _noticeList.value = pagingNoticeList

            }, {
                Log.d(javaClass.simpleName + TEST, "" + it)

            }))

    }
}