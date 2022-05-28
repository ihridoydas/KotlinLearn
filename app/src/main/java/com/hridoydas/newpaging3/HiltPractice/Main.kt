package com.hridoydas.newpaging3.HiltPractice

import android.app.Application
import android.util.Log
import android.widget.Toast
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.security.AccessController.getContext
import javax.inject.Inject
import javax.inject.Singleton


interface One{
    fun getName()

}
class ImpelmentOne @Inject constructor(private val name:String):One{
    override fun getName() {
        Log.d("main", "Ny name is Hridoy chandra das ")
    }

}

class Main @Inject constructor(private val one: One){

    fun getName(){
        one.getName()
    }
}
//Use this
//@Module
//@InstallIn(SingletonComponent::class)
//abstract class AppModule{
//    @Binds
//    @Singleton
//    abstract fun binding(
//        impelmentOne: ImpelmentOne
//    ):One
//}

//Not use This
@Module
@InstallIn(SingletonComponent::class)
class AppModule{
    @Provides
    @Singleton
    fun getName():String ="Hridoy"

    @Provides
    @Singleton
    fun binding(
         name: String
    ):One= ImpelmentOne(name)
}




