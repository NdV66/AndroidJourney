package com.ndv.advancedactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import java.util.*

private const val TIME_UNIT = 60
private const val SECONDS_IN_HOUR = TIME_UNIT * TIME_UNIT
private const val DELAY_MS: Long = 1000;

private const val CURRENT_SECONDS = "currentSeconds"
private const val IS_TIMER_RUNNING = "isRunning"

fun prepareTimeToDisplay(currentSeconds: Long): String {
    val hours = currentSeconds / SECONDS_IN_HOUR
    val minutes = (currentSeconds % SECONDS_IN_HOUR) / TIME_UNIT
    val seconds = currentSeconds % TIME_UNIT

    return String.format("%d:%02d:%02d", hours, minutes, seconds)
}

class MainActivity : AppCompatActivity() {

    private var currentSeconds: Long = 0;
    private var isTimerRunning = false;
    private val handler = Handler(Looper.getMainLooper())

    private val updateTimerRunnable = object : Runnable {
        override fun run() {
            updateTimer()
            if (isTimerRunning) {
                handler.postDelayed(this, DELAY_MS)
                currentSeconds++
            }
        }
    }

    private fun updateTimer() {
        val timer = findViewById<TextView>(R.id.timer)
        timer.text = prepareTimeToDisplay(currentSeconds)
    }

    private fun startTimer() {
        handler.removeCallbacks(updateTimerRunnable)
        handler.post(updateTimerRunnable)
    }

    private fun restoreTimer(savedInstanceState: Bundle) {
        currentSeconds = savedInstanceState.getLong(CURRENT_SECONDS)
        isTimerRunning = savedInstanceState.getBoolean(IS_TIMER_RUNNING)

        if (isTimerRunning) {
            startTimer()
        }
    }

    private fun setupStartButton() {
        val startButton = findViewById<Button>(R.id.start)

        startButton.setOnClickListener {
            isTimerRunning = true
            startTimer()
        }
    }

    private fun setupStopButton() {
        val stopButton = findViewById<Button>(R.id.stop)

        stopButton.setOnClickListener {
            isTimerRunning = false
        }
    }

    private fun setupClearButton() {
        val clearButton = findViewById<Button>(R.id.clear)

        clearButton.setOnClickListener {
            isTimerRunning = false
            currentSeconds = 0
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            restoreTimer(savedInstanceState)
        }

        setupStartButton()
        setupStopButton()
        setupClearButton()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putLong(CURRENT_SECONDS, currentSeconds)
        outState.putBoolean(IS_TIMER_RUNNING, isTimerRunning)
        super.onSaveInstanceState(outState)
    }
}
