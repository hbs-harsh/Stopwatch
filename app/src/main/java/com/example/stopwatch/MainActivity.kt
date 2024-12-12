package com.example.stopwatch

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var isRunning = false
    private var seconds = 0
    private var minutes = 0
    private var hours = 0
    private lateinit var timerText: TextView
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button
    private lateinit var btnReset: Button
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerText = findViewById(R.id.timertext)
        btnStart = findViewById(R.id.btnStart)
        btnStop = findViewById(R.id.btnStop)
        btnReset = findViewById(R.id.btnReset)

        btnStart.setOnClickListener {
            if (!isRunning) {
                startTimer()
            }
        }

        btnStop.setOnClickListener {
            stopTimer()
        }

        btnReset.setOnClickListener {
            resetTimer()
        }
    }

    private fun startTimer() {
        isRunning = true
        btnStart.isEnabled = false
        btnStop.isEnabled = true
        btnReset.isEnabled = true

        handler.postDelayed(object : Runnable {
            override fun run() {
                if (isRunning) {
                    seconds++
                    if (seconds == 60) {
                        seconds = 0
                        minutes++
                    }
                    if (minutes == 60) {
                        minutes = 0
                        hours++
                    }
                    updateTimerText()
                    handler.postDelayed(this, 1000)
                }
            }
        }, 1000)
    }

    private fun stopTimer() {
        isRunning = false
        btnStart.isEnabled = true
        btnStop.isEnabled = false
    }

    private fun resetTimer() {
        isRunning = false
        seconds = 0
        minutes = 0
        hours = 0
        updateTimerText()
        btnStart.isEnabled = true
        btnStop.isEnabled = false
        btnReset.isEnabled = false
    }

    private fun updateTimerText() {
        val formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds)
        timerText.text = formattedTime
    }
}
