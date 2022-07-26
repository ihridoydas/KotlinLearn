package com.example.jetpackcomposepractice.learnTesting.UnitTest


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilsTest {

    @Test
    fun empty_username_returns_false() {
        val result = RegistrationUtils.validateRegistrationInput(
            username = "",
            password = "123",
            confirmPassword = ""
        )
        assertThat(result).isFalse()

    }

    @Test
    fun valid_username_and_correctly_repeated_password_returns_true() {
        val result = RegistrationUtils.validateRegistrationInput(
            username = "Hridoy",
            password = "123",
            confirmPassword = "123"
        )
        assertThat(result).isTrue()

    }

    @Test
    fun username_already_exist_returns_false() {
        val result = RegistrationUtils.validateRegistrationInput(
            username = "Carl",
            password = "123",
            confirmPassword = ""
        )
        assertThat(result).isFalse()

    }

    @Test
    fun incorrctlyConfirmedPasswordReturnsFalse() {
        val result = RegistrationUtils.validateRegistrationInput(
            username = "Hridoy",
            password = "123456789",
            confirmPassword = "abceds"
        )
        assertThat(result).isFalse()

    }

    @Test
    fun emptyPasswordReturnsFalse() {
        val result = RegistrationUtils.validateRegistrationInput(
            username = "Hridoy",
            password = "",
            confirmPassword = ""
        )
        assertThat(result).isFalse()

    }

    @Test
    fun lessThenTwoDigitPasswordReturnsFalse() {
        val result = RegistrationUtils.validateRegistrationInput(
            username = "Hridoy",
            password = "abcdef",
            confirmPassword = "abcdef"
        )
        assertThat(result).isFalse()

    }
}