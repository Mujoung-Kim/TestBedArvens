package com.todaysquare.patterntasknote.data.repositories.remote.business

import com.todaysquare.patterntasknote.data.databases.entities.BusinessNotice
import com.todaysquare.patterntasknote.utils.Constants.Url.Companion.BUSI_URL
import com.todaysquare.patterntasknote.utils.Constants.Url.Companion.IMITATION_BUS

import io.reactivex.Observable
import io.reactivex.Single

import org.jsoup.Jsoup

class BusinessNoticeRemoteDataSourceImpl : BusinessNoticeRemoteDataSource {

    override fun requestNotice(): Single<List<BusinessNotice>> =
        Single.fromObservable(
            Observable.create {
                val noticeList = ArrayList<BusinessNotice>()
                val document = Jsoup.connect(BUSI_URL).get()
                val contentElements = document.select("div[class=b-title-box]").select("a")
                val idElements = document.select("td[class=b-num-box]")

                for ((i, element) in contentElements.withIndex())
                    noticeList.add(BusinessNotice(idElements[i].text(), element.text(),
                        IMITATION_BUS + element.attr("href")))

                it.onNext(noticeList)
                it.onComplete()

            })

    override fun requestMoreNotice(offset: Int): Single<List<BusinessNotice>> =
        Single.fromObservable(
            Observable.create {
                val noticeList = ArrayList<BusinessNotice>()
                val document = Jsoup
                    .connect("$IMITATION_BUS?model=list&&articleLimit=10&article.offset=$offset").get()
                val contentElements = document.select("div[class=b-title-box]").select("a")
                val idElements = document.select("td[class=b-num-box]")

                for ((i, element) in contentElements.withIndex())
                    noticeList.add(BusinessNotice(idElements[i].text(), element.text(),
                        IMITATION_BUS + element.attr("href")))

                it.onNext(noticeList)
                it.onComplete()

            })

}