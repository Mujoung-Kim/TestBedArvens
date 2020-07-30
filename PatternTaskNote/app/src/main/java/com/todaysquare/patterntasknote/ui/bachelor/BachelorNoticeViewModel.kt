package com.todaysquare.patterntasknote.ui.bachelor

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.todaysquare.patterntasknote.data.databases.entities.BachelorNotice
import com.todaysquare.patterntasknote.data.repositories.BachelorNoticeRepository
import com.todaysquare.patterntasknote.ui.base.BaseViewModel
import com.todaysquare.patterntasknote.utils.Constants.Param.Companion.TEST

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BachelorNoticeViewModel(private val bachelorNoticeRepository: BachelorNoticeRepository)
    : BaseViewModel() {

    private val _noticeList = MutableLiveData<ArrayList<BachelorNotice>>()
    val noticeList: LiveData<ArrayList<BachelorNotice>> get() = _noticeList

    fun requestNotice() {
        compositeDisposable.add(bachelorNoticeRepository.requestNotice()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showProgress() }
            .doAfterTerminate { hideProgress() }
            .subscribe({
                _noticeList.value = it as ArrayList<BachelorNotice>?
            }, {
                Log.d(javaClass.simpleName + TEST, "" + it)
            }))

    }

    fun requestMoreNotice(offset: Int) {
        compositeDisposable.add(bachelorNoticeRepository.requestMoreRequest(offset)
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