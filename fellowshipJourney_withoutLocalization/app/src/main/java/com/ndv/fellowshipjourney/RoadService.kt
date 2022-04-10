package com.ndv.fellowshipjourney

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import kotlin.random.Random

class RoadService : Service() {
    private val binder = RoadBinder()
    private val self = this

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    fun getDistance(): Double {
        return Random.nextDouble()
    }

    inner class RoadBinder : Binder() {
        fun getRoad(): RoadService {
            return self
        }
    }
}
