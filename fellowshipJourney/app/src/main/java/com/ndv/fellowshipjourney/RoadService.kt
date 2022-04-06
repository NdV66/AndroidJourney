package com.ndv.fellowshipjourney

import android.Manifest
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Binder
import android.os.IBinder
import androidx.core.content.ContextCompat

private const val TIME_MS: Long = 1000
private const val DISTANCE_M: Float = 1f
private const val METERS_IN_KILOMETERS = 1000.0


class RoadService : Service() {
    private val binder = RoadBinder()
    private val self = this
    private var distanceInMeters = 0.0
    private var lastLocation: Location? = null

    private lateinit var listener: LocationListener
    private lateinit var locationManager: LocationManager

    override fun onCreate() {
        super.onCreate()

        listener = prepareLocationListener()
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        setupLocalization()
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onDestroy() {
        super.onDestroy()

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager.removeUpdates(listener)
        }
    }

    fun getDistance() = distanceInMeters / METERS_IN_KILOMETERS

    private fun setupLocalization() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val provider = locationManager.getBestProvider(Criteria(), true)
        if (provider != null) {
            locationManager.requestLocationUpdates(provider, TIME_MS, DISTANCE_M, listener)
        }
    }

    private fun prepareLocationListener() = LocationListener {
        if (lastLocation != null) {
            distanceInMeters += it.distanceTo(lastLocation)
        }

        lastLocation = it
    }

    inner class RoadBinder : Binder() {
        fun getRoad(): RoadService {
            return self
        }
    }
}
