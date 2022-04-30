package com.example.technopa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

//Всякие функции расширения, можете добавлять

fun Fragment.toast(@StringRes stringRes: String) {
    Toast.makeText(requireContext(), stringRes, Toast.LENGTH_SHORT).show()
}


fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

class AutoClearedValue<T : Any>(val fragment: Fragment) : ReadWriteProperty<Fragment, T> {
    private var _value: T? = null

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(@NonNull owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
                    viewLifecycleOwner?.lifecycle?.addObserver(object : DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            _value = null
                        }
                    })
                }
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return _value ?: throw IllegalStateException(
        )
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        _value = value
    }
}

fun <T : Any> Fragment.autoCleared() =
    AutoClearedValue<T>(this)

interface StepListener {
    fun step(timeNs: Long)
}


class SensorFilter {

    fun sum(array: FloatArray): Float {
        var retval = 0f
        for (i in array.indices) {
            retval += array[i]
        }
        return retval
    }

    fun cross(arrayA: FloatArray, arrayB: FloatArray): FloatArray {
        val retArray = FloatArray(3)
        retArray[0] = arrayA[1] * arrayB[2] - arrayA[2] * arrayB[1]
        retArray[1] = arrayA[2] * arrayB[0] - arrayA[0] * arrayB[2]
        retArray[2] = arrayA[0] * arrayB[1] - arrayA[1] * arrayB[0]
        return retArray
    }

    fun norm(array: FloatArray): Float {
        var retval = 0f
        for (i in array.indices) {
            retval += array[i] * array[i]
        }
        return Math.sqrt(retval.toDouble()).toFloat()
    }


    fun dot(a: FloatArray, b: FloatArray): Float {
        return a[0] * b[0] + a[1] * b[1] + a[2] * b[2]
    }

    fun normalize(a: FloatArray): FloatArray {
        val retval = FloatArray(a.size)
        val norm = norm(a)
        for (i in a.indices) {
            retval[i] = a[i] / norm
        }
        return retval
    }
}

class StepDetector {

    private val ACCEL_RING_SIZE = 50
    private val VEL_RING_SIZE = 10

    // change this threshold according to your sensitivity preferences
    private val STEP_THRESHOLD = 50f

    private val STEP_DELAY_NS = 250000000

    private var accelRingCounter = 0
    private val accelRingX = FloatArray(ACCEL_RING_SIZE)
    private val accelRingY = FloatArray(ACCEL_RING_SIZE)
    private val accelRingZ = FloatArray(ACCEL_RING_SIZE)
    private var velRingCounter = 0
    private val velRing = FloatArray(VEL_RING_SIZE)
    private var lastStepTimeNs: Long = 0
    private var oldVelocityEstimate = 0f

    private var listener: StepListener? = null

    fun registerListener(listener: StepListener) {
        this.listener = listener
    }

    fun updateAccelerometer(timeNs: Long, x: Float, y: Float, z: Float) {
        val currentAccel = FloatArray(3)
        currentAccel[0] = x
        currentAccel[1] = y
        currentAccel[2] = z

        // First step is to update our guess of where the global z vector is.
        accelRingCounter++
        accelRingX[accelRingCounter % ACCEL_RING_SIZE] = currentAccel[0]
        accelRingY[accelRingCounter % ACCEL_RING_SIZE] = currentAccel[1]
        accelRingZ[accelRingCounter % ACCEL_RING_SIZE] = currentAccel[2]

        val worldZ = FloatArray(3)
        worldZ[0] = SensorFilter().sum(accelRingX) / Math.min(accelRingCounter, ACCEL_RING_SIZE)
        worldZ[1] = SensorFilter().sum(accelRingY) / Math.min(accelRingCounter, ACCEL_RING_SIZE)
        worldZ[2] = SensorFilter().sum(accelRingZ) / Math.min(accelRingCounter, ACCEL_RING_SIZE)

        val normalization_factor = SensorFilter().norm(worldZ)

        worldZ[0] = worldZ[0] / normalization_factor
        worldZ[1] = worldZ[1] / normalization_factor
        worldZ[2] = worldZ[2] / normalization_factor

        val currentZ = SensorFilter().dot(worldZ, currentAccel) - normalization_factor
        velRingCounter++
        velRing[velRingCounter % VEL_RING_SIZE] = currentZ

        val velocityEstimate = SensorFilter().sum(velRing)

        if (velocityEstimate > STEP_THRESHOLD && oldVelocityEstimate <= STEP_THRESHOLD
            && timeNs - lastStepTimeNs > STEP_DELAY_NS) {
            listener!!.step(timeNs)
            lastStepTimeNs = timeNs
        }
        oldVelocityEstimate = velocityEstimate
    }
}

fun <T: Fragment> T.withArguments(action: Bundle.() -> Unit): T {
    return apply {
        val args = Bundle().apply(action)
        arguments = args
    }
}

