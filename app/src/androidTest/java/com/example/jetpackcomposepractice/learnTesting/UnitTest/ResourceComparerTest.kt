package com.example.jetpackcomposepractice.learnTesting.UnitTest

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.jetpackcomposepractice.R
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class ResourceComparerTest {
    private lateinit var resourceComparer: ResourceComparer

    @Before
    fun setup(){
        resourceComparer = ResourceComparer()
    }
    @After
    fun teardown(){

    }

    @Test
    fun stringResourceSomeAsGivenString_returnTrue() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context, R.string.app_name,"JetPackComposePractice")
        assertThat(result).isTrue()
    }

    @Test
    fun stringResourceDifferentAsGivenString_returnFalse() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context, R.string.app_name,"AnotherString")
        assertThat(result).isFalse()
    }
}