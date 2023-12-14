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
        isServiceRunning = IsServiceOn.isServiceRunning(this@MainActivity, intent::class.java)
        if (!isServiceRunning) startService(intent) else Log.d(
            StringFetch.tagName,
            StringFetch.serviceRunMessage
        )

        mainBinding.palindromeButton.setOnClickListener {
            onCheckPalindromeButtonSelected()
        }
        mainBinding.checkNumber.setOnClickListener {
            onEOCheckSelected()
        }
        mainBinding.mainLayout.setOnClickListener {
            hideKeyboard(it)
        }
    }

    private fun onEOCheckSelected() {
        val num: String = mainBinding.evenNumField.text.toString()
        CheckEvenOdd.setNumber(num.toInt())
        handler.postDelayed({
                val responseValueEO = EvenOddResponse.getResponseValueEO()
                responseToDisplay(responseValueEO)
            }, 2000
        )
    }

    private fun onCheckPalindromeButtonSelected() {
        Utils.setName(mainBinding.palindromeText.text.toString())
        if (mainBinding.palindromeText.text.toString().equals(Utils.getName(), true)) {
            handler.postDelayed({
                val responseValue = ResponseBackCheck.getResponseValue()
                responseToDisplay(responseValue)
            }, 2000)
        }
    }

    private fun responseToDisplay(responseValue: Int) {
        when (responseValue) {
            0 -> Toast.makeText(this, "${Utils.getName()} ${StringFetch.notPalindromeMessage}", Toast.LENGTH_LONG)
                .show()

            1 -> Toast.makeText(this, "${Utils.getName()} ${StringFetch.palindromeMessage}", Toast.LENGTH_LONG)
                .show()

            2 -> Toast.makeText(
                this@MainActivity,
                "${CheckEvenOdd.getNumber()} ${StringFetch.evenMessage}",
                Toast.LENGTH_LONG
            ).show()

            3 -> Toast.makeText(
                this@MainActivity,
                "${CheckEvenOdd.getNumber()} ${StringFetch.oddMessage}",
                Toast.LENGTH_LONG
            ).show()

            else -> Log.d(StringFetch.tagName, "${StringFetch.logMessageForWhen} $responseValue")
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