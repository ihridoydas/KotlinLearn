package com.hridoydas.newpaging3.HiltPractice


import android.util.Log
import javax.inject.Inject

class Car @Inject constructor(private val engine: Engine, private var wheel: Wheel) {
    fun getCar() {

        wheel.getWheel()
        engine.getEngine()
        Log.d("main", "Car is started")


    }


}