package com.todaysquare.patterntasknote.data.repositories.remote

import com.todaysquare.patterntasknote.data.databases.entities.BachelorNotice
import io.reactivex.Single

interface BachelorNoticeRemoteDataSource {

    fun requestNotice(): Single<List<BachelorNotice>>
    fun requestMoreNotice(offset: Int): Single<List<BachelorNotice>>

}