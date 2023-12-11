package com.anxer.part1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.anxer.part1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
   // private var isServingRunning : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
       val intent = Intent(this@MainActivity,Part1Service::class.java)
       startService(intent)
       mainBinding.palindromeButton.setOnClickListener {
            Utils.setName(mainBinding.palindromeText.text.toString())
            Log.d("part1Service","Setting Name: ${Utils.getName()}")
            val responseValue = ResponseBackCheck.getResponseValue()
            Log.d("part1Service","Response from part2: ${responseValue.toString()}")
        }
    }
}