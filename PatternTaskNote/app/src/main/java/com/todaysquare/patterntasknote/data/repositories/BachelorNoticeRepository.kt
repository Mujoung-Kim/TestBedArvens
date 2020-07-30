package com.todaysquare.patterntasknote.data.repositories

import com.todaysquare.patterntasknote.data.databases.entities.BachelorNotice
import io.reactivex.Flowable
import io.reactivex.Single

interface BachelorNoticeRepository {

    fun requestNotice(): Flowable<List<BachelorNotice>>
    fun requestMoreRequest(offset: Int): Single<List<BachelorNotice>>

}