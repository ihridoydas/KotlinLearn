package com.hridoydas.newpaging3.HiltPractice

import android.util.Log
import javax.inject.Inject

class Engine {

    @Inject
    constructor()
    fun getEngine(){


        Log.d("main", "getEngine: Started Engine ")
    }
}