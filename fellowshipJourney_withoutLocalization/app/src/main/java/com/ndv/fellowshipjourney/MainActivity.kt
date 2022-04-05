package com.ndv.fellowshipjourney

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


const val DELAY_MS: Long = 1000

class MainActivity : AppCompatActivity() {
    private val serviceConnection = prepareServiceConnection()
    private var bound = false
    private lateinit var roadService: RoadService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showJourneyDistance()
    }

    override fun onStart() {
        super.onStart()

        val intent = Intent(this, RoadService::class.java)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()

        if (bound) {
            unbindService(serviceConnection)
            bound = false
        }
    }

    private fun prepareServiceConnection() =
        object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                val binder = service as RoadService.RoadBinder
                bound = true
                roadService = binder.getRoad()
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                bound = false
            }
        }

    private fun setDistanceText(distance: String) {
        val distanceTextView = findViewById<TextView>(R.id.distance)
        distanceTextView.text = distance
    }

    private fun showJourneyDistance() {
        val mainHandler = Handler(Looper.getMainLooper())
        val task = object: Runnable {
            override fun run() {
                if(bound) {
                    val distance = roadService.getDistance().toString()
                    setDistanceText(distance)
                }
                mainHandler.postDelayed(this, DELAY_MS)
            }
        }

        mainHandler.post(task)
    }
}
