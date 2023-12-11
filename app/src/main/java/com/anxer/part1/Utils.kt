package com.anxer.part1

object Utils {

    var nameToCheck : String = "Default"

    fun setName(name:String) {
        nameToCheck = name
    }

    fun getName() = nameToCheck
}

object ResponseBackCheck {

    var responseBack : Int = 99

    fun setResponse(backValue : Int){
        responseBack = backValue
    }

    fun getResponseValue() = responseBack
}