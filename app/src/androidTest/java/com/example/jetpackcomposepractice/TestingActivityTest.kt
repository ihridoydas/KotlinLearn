package com.example.jetpackcomposepractice

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.jetpackcomposepractice.learnTesting.TASK_LIST_TEST_TAG
import com.example.jetpackcomposepractice.learnTesting.Task
import com.example.jetpackcomposepractice.learnTesting.TaskListScreen
import com.example.jetpackcomposepractice.learnTesting.TestingActivity
import com.example.jetpackcomposepractice.navigationPassingDataNavHost.ui.theme.JetPackComposePracticeTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestingActivityTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<TestingActivity>()

    //Test Task list screen if task are empty
   // val tasks_empty = emptyList<Task>()

    @Test
    fun testTaskListScreenIfTasksAreEmpty(){
        composeTestRule.setContent {
            JetPackComposePracticeTheme{
                TaskListScreen(emptyList())
            }
        }
        composeTestRule.onNodeWithTag(TASK_LIST_TEST_TAG)
            .onChildren()
            .assertCountEquals(0)

    }


    fun getTaskList():List<Task> = listOf(
        Task("I am Hridoy"),
        Task("I am Chandra"),
        Task("I am Das"),
        Task("I am Another Man"),
    )

    @Test
    fun testTaskListScreenIdTaskIsNotEmpty(){
        composeTestRule.setContent {
            JetPackComposePracticeTheme{
                TaskListScreen(getTaskList())
            }
        }
        composeTestRule.onNodeWithTag(TASK_LIST_TEST_TAG)
            .onChildren()
            .assertCountEquals(getTaskList().size)

    }

    @Test
    fun testFirstAndLastDesription(){
        composeTestRule.setContent {
            JetPackComposePracticeTheme{
                TaskListScreen(getTaskList())
            }
        }

        composeTestRule.apply{
            onNodeWithTag(TASK_LIST_TEST_TAG)
                .onChildren()
                .onFirst()
                .assert(hasText("I am Hridoy"))

            onNodeWithTag(TASK_LIST_TEST_TAG)
                .onChildren()
                .onLast()
                .assert(hasText("I am Another Man"))
        }


        // Check Coloumn is Scrollable
//            composeTestRule.onNodeWithTag("testScroll")
//                .assert(hasScrollAction())
//            composeTestRule.waitForIdle()

//        fun findNode(): SemanticsNodeInteraction {
//            return composeTestRule.onNodeWithTag(
//                testTag = "testScroll",
//            )
//        }
//        fun assertNodeIsScrollable() {
//            findNode().assert(hasScrollAction())
//        }
//        composeTestRule.apply {
//            assertNodeIsScrollable()
//        }
//        composeTestRule.waitForIdle()








    }






}