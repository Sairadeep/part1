package com.anxer.part1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.anxer.part1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private val handler = Handler(Looper.getMainLooper())
    private var isServiceRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
        val intent = Intent(this@MainActivity, Part1Service::class.java)
        isServiceRunning = IsServiceOn.isServiceRunning(this@MainActivity,intent::class.java)
        if(!isServiceRunning) startService(intent) else Log.d("part1Service","Service is already running.")
        mainBinding.palindromeButton.setOnClickListener {
            Utils.setName(mainBinding.palindromeText.text.toString())
            Log.d("part1Service", "Setting Name: ${Utils.getName()}")
            if (mainBinding.palindromeText.text.toString().equals(Utils.getName(), true)) {
                Log.d("part1Service", mainBinding.palindromeText.text.toString())
                Log.d("part1Service", Utils.getName())
                handler.postDelayed(
                    {
                        val responseValue = ResponseBackCheck.getResponseValue()
                        Log.d(
                            "part1Service",
                            "Response from part2 in communication from part3: $responseValue"
                        )
                        responseToDisplay(responseValue)
                    }, 2000
                )
            }
        }
        mainBinding.checkNumber.setOnClickListener {
            val num: String = mainBinding.evenNumField.text.toString()
            CheckEvenOdd.setNumber(num.toInt())
            Log.d("part1Service", "NUM ${CheckEvenOdd.getNumber()}")
            handler.postDelayed(
                {
                    val responseValueEO = EvenOddResponse.getResponseValueEO()
                    Log.d(
                        "part1Service",
                        "Response from part2 in communication from part4: $responseValueEO"
                    )
                    responseToDisplay(responseValueEO)
                }, 2000
            )
        }
        mainBinding.mainLayout.setOnClickListener {
            hideKeyboard(it)
        }
    }

    private fun responseToDisplay(responseValue: Int) {
        when (responseValue) {
            0 -> Toast.makeText(this, "${Utils.getName()} is not a palindrome", Toast.LENGTH_LONG)
                .show()

            1 -> Toast.makeText(this, "${Utils.getName()} is a palindrome", Toast.LENGTH_LONG)
                .show()

            2 -> Toast.makeText(
                this@MainActivity,
                "${CheckEvenOdd.getNumber()} is an Even number",
                Toast.LENGTH_LONG
            ).show()

            3 -> Toast.makeText(
                this@MainActivity,
                "${CheckEvenOdd.getNumber()} is an Odd number",
                Toast.LENGTH_LONG
            ).show()

            else -> Log.d("part1Service", "$responseValue is received.")
        }
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            view.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }


}