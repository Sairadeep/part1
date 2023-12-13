package com.anxer.part1

import android.app.ActivityManager
import android.content.Context

object Utils {

    private var nameToCheck : String= "Default"

    fun setName(name:String) {
        nameToCheck = name
    }

    fun getName() = nameToCheck
}

object ResponseBackCheck {

    private var responseBack : Int = 99

    fun setResponse(backValue : Int){
        responseBack = backValue
    }

    fun getResponseValue() = responseBack
}

object CheckEvenOdd {

    private var defaultEven = 2

    fun setNumber(eon : Int) {
        defaultEven = eon
    }

    fun getNumber() = defaultEven
}

object EvenOddResponse {

    private var eoresponseBack : Int = 99

    fun setResponse(backValue : Int){
        eoresponseBack = backValue

    }

    fun getResponseValueEO() = eoresponseBack
}


object IsServiceOn {
    @Suppress("Deprecation")
    fun isServiceRunning(context: Context, serviceClass: Class<*>): Boolean {
        val manager: ActivityManager =
            context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val services = manager.getRunningServices(Int.MAX_VALUE)
        for (service in services) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }
}