package com.practice.logindemo.presentation.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var userName = mutableStateOf("")
    var password = mutableStateOf("")
    var errorMessage = mutableStateOf("")


    fun validateLogin(): Boolean {
        errorMessage.value = ""
        if (userName.value.isEmpty() || userName.value.length <= 8) {
            errorMessage.value = "User Name should not be empty or less than 8 chars"
            return false
        }
        if (password.value.length < 6) {
            errorMessage.value = "Password must be at least 6 characters"
            return false
        }
        return true
    }
}