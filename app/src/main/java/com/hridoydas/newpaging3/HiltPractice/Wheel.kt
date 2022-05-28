package com.hridoydas.newpaging3.HiltPractice

import android.util.Log
import javax.inject.Inject

class Wheel {

    @Inject
    constructor()
    fun getWheel(){
        Log.d("main", "getWheel:wheel is started ")
    }
}