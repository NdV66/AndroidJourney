package com.ndv.fellowshipjourney

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private val serviceConnection = prepareServiceConnection()
    private var bound = false
    private lateinit var roadService: RoadService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        val intent = Intent(this, RoadService::class.java)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()

        if(bound) {
            unbindService(serviceConnection)
            bound = false
        }
    }

    private fun prepareServiceConnection(): ServiceConnection {
        return object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                val binder = service as RoadService.RoadBinder
                bound = true
                roadService = binder.getRoad()
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                bound = false
            }
        }
    }
}