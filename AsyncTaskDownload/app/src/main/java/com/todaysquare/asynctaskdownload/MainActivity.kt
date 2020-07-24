package com.todaysquare.asynctaskdownload

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Toast
import com.todaysquare.asynctaskdownload.data.WorkerRunnable
import com.todaysquare.asynctaskdownload.data.WorkerThread
import com.todaysquare.asynctaskdownload.utils.Constants.Param.Companion.TEST
import kotlinx.android.synthetic.main.activity_main.*

/*
    * 백그라운드 thread 는 UI 구성요소에 접근하면 안된다.
        -> main thread 이외에 다른 thread 는 UI 업데이트 불가

    * 핸들러와 루퍼 작동 원리
        1. Main thread 는 내부적으로 루퍼를 가지고 루퍼는 message queue 를 포함
        2. Message queue 는 other thread or thread 자기 자신으로부터 전달받은 메시지를 보관하는 queue 이다.
        3. 루퍼는 message queue 에서 메시지, runnable 객체를 순서대로 꺼내 핸들러가 처리하도록 한다.
        4. 핸들러는 루퍼로부터 받은 메시지, runnable 객체를 처리하거나 메시지를 받아서 message queue 에 넣는 thread 간 통신장치이다.

    * 용어 정리
        1. 루퍼 : 여러 개의 백그라운드에서 큐에 넣은 메시지를 FIFO 방식으로 핸들러에 전달한다.
        2. 핸들러 : Main thread 에서 사용되며 thread 와 Main thread 와의 통신을 담당한다.
        3. 메시지 : 객체에 미리 정의해둔 코드를 입력하고, 큐에 담아두면 루퍼가 꺼내서 핸들러에 전달한다.
                -> 루퍼의 큐 값을 전달하기 위해 사용되는 클래스(객체)이다.

*/
class MainActivity : AppCompatActivity() {
    var total = 0
    var operation = false
    private val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            val minute = String.format("%02d", total/60)
            val second = String.format("%02d", total%60)

            textTimer.text = "$minute:$second"

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //  백그라운드 thread & runnable MainActivity 에서 호출 하는 법
        val thread = WorkerThread()
        val runnable = Thread(WorkerRunnable())

        //  thread 로 구현
        thread.start()

        //  runnable 로 구현
        runnable.start()

        //  람다식으로 runnable 익명객체 구현
        Thread {
            var i = 20

            while (i > 10) {
                i -= 1
                Log.i(javaClass.simpleName + TEST, i.toString())

            }
        }.start()

        //  kotlin 에서 제공하는 기본 thread()
        kotlin.concurrent.thread(start = true) {
            var i = 0

            while (i < 10) {
                i += 1
                Log.i(javaClass.simpleName + TEST, i.toString())

            }
        }

        //  thread 이용한 timer
        buttonStart.setOnClickListener {
            operation = true

            kotlin.concurrent.thread(start = true) {
                while (operation) {
                    Thread.sleep(1000L)

                    if (operation) {
                        total += 1
                        handler.sendEmptyMessage(0)

                    }
                }
            }
        }

        buttonStop.setOnClickListener {
            if (operation) {
                operation = false
                total = 0
                textTimer.text = "00:00"

            }
        }
    }
}