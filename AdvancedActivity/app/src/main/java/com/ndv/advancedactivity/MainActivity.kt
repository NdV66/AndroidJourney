package com.ndv.advancedactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.IdRes
import java.util.*

private const val TIME_UNIT = 60
private const val SECONDS_IN_HOUR = TIME_UNIT * TIME_UNIT
private const val DELAY_MS: Long = 1000;

private const val CURRENT_SECONDS = "currentSeconds"
private const val IS_TIMER_RUNNING = "isRunning"
private const val WAS_TIMER_RUNNING = "wasRunning"

fun prepareTimeToDisplay(currentSeconds: Long): String {
    val hours = currentSeconds / SECONDS_IN_HOUR
    val minutes = (currentSeconds % SECONDS_IN_HOUR) / TIME_UNIT
    val seconds = currentSeconds % TIME_UNIT

    return String.format("%d:%02d:%02d", hours, minutes, seconds)
}

class MainActivity : AppCompatActivity() {
    private var currentSeconds: Long = 0;
    private var isTimerRunning = false;
    private var wasTimerRunning = false;
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
        wasTimerRunning = savedInstanceState.getBoolean(WAS_TIMER_RUNNING)

        if (isTimerRunning) {
            startTimer()
        }
    }

    private fun setupButton(id: Int, action: (View) -> Unit) {
        val button = findViewById<Button>(id)
        button.setOnClickListener(action)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            restoreTimer(savedInstanceState)
        }

        setupButton(R.id.start) {
            isTimerRunning = true
            startTimer()
        }

        setupButton(R.id.stop) {
            isTimerRunning = false
        }

        setupButton(R.id.clear) {
            isTimerRunning = false
            currentSeconds = 0
        }

        setupButton(R.id.share) {
           val intent = Intent().apply {
               type = "text/plain"
               action = Intent.ACTION_SEND
               putExtra(Intent.EXTRA_TEXT, prepareTimeToDisplay(currentSeconds))
           }
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putLong(CURRENT_SECONDS, currentSeconds)
        outState.putBoolean(IS_TIMER_RUNNING, isTimerRunning)
        outState.putBoolean(WAS_TIMER_RUNNING, wasTimerRunning)
        super.onSaveInstanceState(outState)
    }

    override fun onPause() {
        super.onPause()
        println("ON PAUSE!!!")
        wasTimerRunning = isTimerRunning
        isTimerRunning = false
    }

    override fun onResume() {
        super.onResume()
        if (wasTimerRunning) {
            isTimerRunning = true
            startTimer()
        }
    }
}
