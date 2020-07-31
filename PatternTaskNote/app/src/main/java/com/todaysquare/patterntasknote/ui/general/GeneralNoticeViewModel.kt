package com.todaysquare.patterntasknote.ui.general

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.todaysquare.patterntasknote.data.databases.entities.GeneralNotice
import com.todaysquare.patterntasknote.data.repositories.GeneralNoticeRepository
import com.todaysquare.patterntasknote.ui.base.BaseViewModel
import com.todaysquare.patterntasknote.utils.Constants.Param.Companion.TEST

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GeneralNoticeViewModel(private val generalNoticeRepository: GeneralNoticeRepository) : BaseViewModel() {
    private val _noticeList = MutableLiveData<ArrayList<GeneralNotice>>()
    val noticeList: LiveData<ArrayList<GeneralNotice>> get() = _noticeList

    fun requestNotice() {
        compositeDisposable.add(generalNoticeRepository.requestNotice()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showProgress() }
            .doAfterTerminate { hideProgress() }
            .subscribe({
                _noticeList.value = it as ArrayList<GeneralNotice>?

            }, {
                Log.d(javaClass.simpleName + TEST, "" + it)

            }))

    }

    fun requestMoreNotice(offset: Int) {
        compositeDisposable.add(generalNoticeRepository.requestMoreNotice(offset)
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