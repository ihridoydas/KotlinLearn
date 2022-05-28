package com.hridoydas.newpaging3.HiltPractice

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Qualifier

class Main2 @Inject constructor(
    @FName
    private val fName:String,
    @LName
    private val lName:String){

    fun getName(){
        Log.d("main2","My Name is $fName $lName")
    }
}
@Module
@InstallIn(SingletonComponent::class)
class ModuleApp{

    @Provides
    @FName
    fun getFName():String="DAS"

    @Provides
    @LName
    fun getLName():String="Hridoy"

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FName

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LName