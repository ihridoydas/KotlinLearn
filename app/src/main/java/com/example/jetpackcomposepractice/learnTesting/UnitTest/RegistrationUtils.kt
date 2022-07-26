package com.example.jetpackcomposepractice.learnTesting.UnitTest

object RegistrationUtils {
    private val existingUsers = listOf("Peter", "Carl")


    /**
     * Validate registration input
     * the input is not valid if...
     * ...the username /password is empty
     * ...the username is already taken
     * ... confirm password is password isn@t the same as the real password
     * ... the password contains less 2 digits
     *
     * @param username
     * @param password
     * @param confirmPassword
     * @return
     */

    fun validateRegistrationInput(
        username: String,
        password: String,
        confirmPassword: String,
    ): Boolean {
        if (username.isEmpty() || password.isEmpty()) {
            return false
        }
        if (username in existingUsers) {
            return false
        }
        if (password != confirmPassword) {
            return false
        }
        if (password.count { it.isDigit() } < 2) {
            return false
        }
        return true
    }
}