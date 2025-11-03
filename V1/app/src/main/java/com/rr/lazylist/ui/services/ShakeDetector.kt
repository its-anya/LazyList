package com.rr.lazylist.ui.services

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShakeDetector(
    private val sensorManager: SensorManager
) : SensorEventListener {

    private val _shakeDetected = MutableStateFlow(false)
    val shakeDetected: StateFlow<Boolean> = _shakeDetected.asStateFlow()

    private val accelerometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    private var lastUpdate: Long = 0
    private var lastX: Float = 0f
    private var lastY: Float = 0f
    private var lastZ: Float = 0f

    private val SHAKE_THRESHOLD = 800
    private val TIME_THRESHOLD = 300

    fun start() {
        accelerometer?.let {
            sensorManager.registerListener(
                this,
                it,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    fun stop() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            val currentTime = System.currentTimeMillis()

            if ((currentTime - lastUpdate) > TIME_THRESHOLD) {
                val diffTime = (currentTime - lastUpdate)
                lastUpdate = currentTime

                val x = it.values[0]
                val y = it.values[1]
                val z = it.values[2]

                val speed = kotlin.math.abs(x + y + z - lastX - lastY - lastZ) / diffTime * 10000

                if (speed > SHAKE_THRESHOLD) {
                    _shakeDetected.value = true
                    // Reset after a short delay
                    android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                        _shakeDetected.value = false
                    }, 500)
                }

                lastX = x
                lastY = y
                lastZ = z
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Not needed
    }
}

