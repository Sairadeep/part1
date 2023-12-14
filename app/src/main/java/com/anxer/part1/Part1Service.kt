package com.anxer.part1

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class Part1Service : Service() {
    private var isServiceRunning = false

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val part2ServiceStart = Intent("MyPart2Service")
        part2ServiceStart.setPackage("com.anxer.part2")
        isServiceRunning = IsServiceOn.isServiceRunning(this,part2ServiceStart::class.java)
        if(!isServiceRunning) startService(part2ServiceStart) else Log.d(StringFetch.tagName,StringFetch.serviceRunMessage)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    private var binder = object : IOneAidlInterface.Stub() {
        override fun sendName(): String {
            return Utils.getName()
        }

        override fun checkBack(response: Int) {
            ResponseBackCheck.setResponse(response)
        }

        override fun sendNumber(): Int {
            return CheckEvenOdd.getNumber()
        }

        override fun checkEvenOddCallBack(eo: Int) {
            EvenOddResponse.setResponse(eo)
        }
    }
}