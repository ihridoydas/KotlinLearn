package com.example.jetpackcomposepractice

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.jetpackcomposepractice.learnTesting.TestingActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CounterTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<TestingActivity>()

    @Test
    fun counter_initially_zero(){
        val text = composeTestRule.activity.getString(R.string.clicks,0)
        composeTestRule.onNodeWithText(text).assertExists()
    }

    @Test
    fun clickButton_increament(){
        val textIncrement = composeTestRule.activity.getString(R.string.increment_counter)
        val textClick = composeTestRule.activity.getString(R.string.clicks,1)
        composeTestRule.onNodeWithText(textIncrement).performClick()
        composeTestRule.onNodeWithText(textClick).assertExists()

    }

}