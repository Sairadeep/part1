package com.anxer.part1

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class Part1Service : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("part1Service", "Part1Service started, starting part2 service now")
        val part2ServiceStart = Intent("MyPart2Service")
        part2ServiceStart.setPackage("com.anxer.part2")
        startService(part2ServiceStart)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    private var binder = object : IOneAidlInterface.Stub() {
        override fun sendName(): String {
            Log.d("part1Service", "getName(): ${Utils.getName()}")
            return Utils.getName()
        }

        override fun checkBack(response: Int) {
            Log.d("part1Service", "checkBack response: $response")
            ResponseBackCheck.setResponse(response)
            Log.d("part1Serviced", "${ResponseBackCheck.getResponseValue()}")
        }

        override fun sendNumber(): Int {
            Log.d("part1Service","Sent ${CheckEvenOdd.getNumber()}")
            return CheckEvenOdd.getNumber()
        }

        override fun checkEvenOddCallBack(eo: Int) {
            EvenOddResponse.setResponse(eo)
        }
    }
}