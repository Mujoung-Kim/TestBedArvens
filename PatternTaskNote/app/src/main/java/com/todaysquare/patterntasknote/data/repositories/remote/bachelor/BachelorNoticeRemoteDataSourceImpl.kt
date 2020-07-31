package com.todaysquare.patterntasknote.data.repositories.remote.bachelor

import com.todaysquare.patterntasknote.data.databases.entities.BachelorNotice
import com.todaysquare.patterntasknote.utils.Constants.Url.Companion.BASE_URL
import com.todaysquare.patterntasknote.utils.Constants.Url.Companion.IMITATION_URL

import io.reactivex.Observable
import io.reactivex.Single

import org.jsoup.Jsoup

class BachelorNoticeRemoteDataSourceImpl :
    BachelorNoticeRemoteDataSource {

    override fun requestNotice(): Single<List<BachelorNotice>> =
        Single.fromObservable {
            Observable.create<ArrayList<BachelorNotice>> {
                val bachelorNoticeList = ArrayList<BachelorNotice>()
                val document = Jsoup.connect(BASE_URL).get()
                val contentElements = document.select("div[class=b-title-box]")
                    .select("a")
                val idElements = document.select("td[class=b-num-box]")

                for ((i, element) in contentElements.withIndex())
                    bachelorNoticeList.add(BachelorNotice(idElements[i].text(), element.text(),
                            IMITATION_URL + element.attr("href")))

                it.onNext(bachelorNoticeList)
                it.onComplete()

            }
        }


    override fun requestMoreNotice(offset: Int): Single<List<BachelorNotice>> =
        Single.fromObservable {
            Observable.create<ArrayList<BachelorNotice>> {
                val bachelorNoticeList = ArrayList<BachelorNotice>()
                val document = Jsoup
                    .connect("$IMITATION_URL?mode=list&&articleLimit=10&article.offset=$offset").get()
                val contentElements = document.select("div[class=b-title-box]")
                    .select("a")
                val idElements = document.select("td[class=b-num-box]")

                for ((i, element) in contentElements.withIndex())
                    if (idElements[i].text() != "공지")
                        bachelorNoticeList.add(BachelorNotice(idElements[i].text(), element.text(),
                        IMITATION_URL + element.attr("href")))

                it.onNext(bachelorNoticeList)
                it.onComplete()

            }
        }

}

