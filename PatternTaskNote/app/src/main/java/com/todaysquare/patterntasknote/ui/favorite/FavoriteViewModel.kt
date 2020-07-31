package com.todaysquare.patterntasknote.ui.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.todaysquare.patterntasknote.data.databases.entities.FavoriteNotice
import com.todaysquare.patterntasknote.data.repositories.FavoriteNoticeRepository
import com.todaysquare.patterntasknote.ui.base.BaseViewModel
import com.todaysquare.patterntasknote.utils.Constants.Param.Companion.TEST

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FavoriteViewModel(private val repository: FavoriteNoticeRepository) : BaseViewModel() {
    private val _noticeList = MutableLiveData<ArrayList<FavoriteNotice>>()
    val noticeList: LiveData<ArrayList<FavoriteNotice>> get() = _noticeList

    fun requestNotice() {
        compositeDisposable.add(repository.requestNotice()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showProgress() }
            .doAfterTerminate { hideProgress() }
            .subscribe({
                _noticeList.value = it as ArrayList<FavoriteNotice>?

            }, {
                Log.d(javaClass.simpleName + TEST, "" + it)

            }))

    }

    fun insert(notice: FavoriteNotice) {
        compositeDisposable.add(repository.insertNotice(notice)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showProgress() }
            .doAfterTerminate { hideProgress() }
            .subscribe({
            }, {
                Log.d(javaClass.simpleName + TEST, "" + it)

            }))

    }

    fun delete(notice: FavoriteNotice) {
        compositeDisposable.add(repository.deleteNotice(notice)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showProgress() }
            .doAfterTerminate { hideProgress() }
            .subscribe({
            }, {
                Log.d(javaClass.simpleName + TEST, "" + it)

            }))

    }
}